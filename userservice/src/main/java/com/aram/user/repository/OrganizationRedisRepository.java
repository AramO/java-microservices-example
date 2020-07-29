package com.aram.user.repository;

import com.aram.user.model.OrganizationRedis;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aram
 */
@Repository
public interface OrganizationRedisRepository extends CrudRepository<OrganizationRedis, String>  {
    Optional<OrganizationRedis> findOneByOrganizationId(Long orgId);
    void removeByOrganizationId(Long orgId);
}
