package com.aram.user.events.handlers;

import com.aram.user.events.CustomChannels;
import com.aram.user.events.models.OrganizationChangeModel;
import com.aram.user.model.OrganizationRedis;
import com.aram.user.repository.OrganizationRedisRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author aram
 */
@EnableBinding(CustomChannels.class)
@Slf4j
public class OrganizationChangeHandler {

    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;
    
    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrganizationChangeModel orgChange) {
        log.debug("Received a message of type " + orgChange.getType());
        switch(orgChange.getAction()) {
            case "GET":
                log.info("Received a GET event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "SAVE":
                log.info("Received a SAVE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "UPDATE":
                log.info("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrganizationId());
                try {
                    Optional<OrganizationRedis> orgRedis = this.organizationRedisRepository.findOneByOrganizationId(orgChange.getOrganizationId());
                    if( orgRedis.isPresent() ) {
                        log.info("orgRedis is present orgRedis-", orgRedis.get());
                        this.organizationRedisRepository.deleteById(orgRedis.get().getId());
                    }
                    
                } catch( Exception e ) {
                    log.info("Could not remove organization-{} excp-{}", orgChange.getOrganizationId(), e.getLocalizedMessage());
                }
                break;
            case "DELETE":
                log.info("Received a DELETE event from the organization service for organization id {}", orgChange.getOrganizationId());
//                this.organizationRedisRepository.removeByOrganizationId(orgChange.getOrganizationId());
                break;
            default:
                log.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getType());
                break;
        }
    }

}
