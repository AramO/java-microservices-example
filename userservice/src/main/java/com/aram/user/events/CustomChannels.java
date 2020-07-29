package com.aram.user.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author aram
 */
public interface CustomChannels {
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}