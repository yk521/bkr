package bkr.core.user.repoistory;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import bkr.core.user.entity.Permission;

/**
 * 权限表库
 * 
 * @author chengd
 */
@Repository
public interface PermissionRepository extends
        PagingAndSortingRepository<Permission, Long>,
        QueryDslPredicateExecutor<Permission> {

}
