package com.aram.user.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 *
 * @author aram
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("organization")
public class OrganizationRedis implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    private String id;
    
    @Indexed
    private Long organizationId;
    
    private String name;
}
