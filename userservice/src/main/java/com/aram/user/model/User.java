package com.aram.user.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author aram
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ar_user")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private Long organizationId;

    private String organizationName;
    
    private String name;

    private String email;

    private String phone;

}
