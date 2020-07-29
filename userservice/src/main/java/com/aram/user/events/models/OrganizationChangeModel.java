package com.aram.user.events.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aram
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationChangeModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String type;
    private String action;
    private Long organizationId;
    private String correlationId;  
}
