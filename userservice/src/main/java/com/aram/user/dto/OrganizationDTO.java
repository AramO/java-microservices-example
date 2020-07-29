package com.aram.user.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aram
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
}
