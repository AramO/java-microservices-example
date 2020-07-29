package com.aram.organization.services;

import com.aram.organization.events.source.SimpleSourceBean;
import com.aram.organization.model.Organization;
import com.aram.organization.repository.OrganizationRepository;
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
public class OrganizationService {
    
    @Autowired
    private OrganizationRepository orgRepository;
    
    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public Optional<Organization> getOrg(Long organizationId) {
        return this.orgRepository.findById(organizationId);
    }

    public Organization saveOrg(Organization org){
        Organization organization = this.orgRepository.save(org);
        this.simpleSourceBean.publishOrgChange("SAVE", organization.getId());
        return organization;
    }

    public Organization updateOrg(Organization org) {
        log.info("updateOrg: org id {} ", org.getId());
        Organization organization = this.orgRepository.save(org);
        this.simpleSourceBean.publishOrgChange("UPDATE", organization.getId());
        return organization;
    }

    public void deleteOrg(Organization org){
        this.orgRepository.delete(org);
    }
}
