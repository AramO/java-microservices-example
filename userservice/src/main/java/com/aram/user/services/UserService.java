package com.aram.user.services;

import com.aram.user.clients.OrganizationClient;
import com.aram.user.dto.OrganizationDTO;
import com.aram.user.model.OrganizationRedis;
import com.aram.user.model.User;
import com.aram.user.repository.OrganizationRedisRepository;
import com.aram.user.repository.UserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aram
 */
@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrganizationClient organizationClient;
    
    @Autowired
    private OrganizationRedisRepository orgRedisRepo;
    
    private Optional<OrganizationRedis> checkRedisCache(Long organizationId) {
        try {
            return this.orgRedisRepo.findOneByOrganizationId(organizationId);
        }
        catch (Exception ex){
            log.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
            return Optional.empty();
        }
    }

    private void cacheOrganizationObject(OrganizationRedis org) {
        try {
            this.orgRedisRepo.save(org);
        }catch (Exception ex){
            log.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
        }
    }

    public Optional<User> getUser(String userId) {
        Optional<User> user = this.userRepository.findById(userId);
        
        if( user.isPresent() ) {
            Long organizationId = user.get().getOrganizationId();
            Optional<OrganizationRedis> organization = checkRedisCache(organizationId);
            if( organization.isPresent() ) {
                user.get().setOrganizationName(organization.get().getName());
            } else {
                Optional<OrganizationDTO> org = this.organizationClient.getOrganization(organizationId);
                if( org.isPresent() ) {
                    OrganizationRedis organizationRedis = new OrganizationRedis();
                    organizationRedis.setOrganizationId(org.get().getId());
                    organizationRedis.setName(org.get().getName());
                    this.cacheOrganizationObject(organizationRedis);
                    user.get().setOrganizationName(org.get().getName());
                }
            }
        }
        
        return user;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
