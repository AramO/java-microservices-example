package com.aram.organization.controllers;

import com.aram.organization.model.Organization;
import com.aram.organization.services.OrganizationService;
import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="/v1/organizations")
@Api(tags = "Organization API")
@Slf4j
public class OrganizationController {
    
    @Autowired
    private OrganizationService orgService;


    @GetMapping(path="/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrganization( @PathVariable("organizationId") Long organizationId) {
        log.info("getOrganization organizationId " + organizationId);
        Optional<Organization> org = this.orgService.getOrg(organizationId);
        if( !org.isPresent() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } 
        return ResponseEntity.status(HttpStatus.OK).body(org.get());
    }

    @PutMapping(path="/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrganization( @PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        Organization organization = this.orgService.updateOrg(org);
        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }

    @PostMapping(path="/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrganization(@RequestBody Organization org) {
        Organization organization = orgService.saveOrg(org);
        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }

    @DeleteMapping(path="/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization( @PathVariable("orgId") String orgId,  @RequestBody Organization org) {
        this.orgService.deleteOrg(org);
    }
}
