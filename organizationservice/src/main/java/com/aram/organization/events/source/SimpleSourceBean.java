package com.aram.organization.events.source;

import com.aram.organization.events.models.OrganizationChangeModel;
import com.aram.organization.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
@Slf4j
public class SimpleSourceBean {
    
    @Autowired
    private Source source;

    public void publishOrgChange(String action, Long orgId){
       log.info("Sending Kafka message {} for Organization Id: {}", action, orgId);
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId()
        );

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
