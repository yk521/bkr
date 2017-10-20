package bkr.core.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * 权限表 model
 * 
 * @author chengd
 */
@Entity
@Table(name = "permission")
@Data
public class Permission {
    /** 权限Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private Long permissionId;

    /** 权限名 */
    private String name;  

    /** 权限名 */
    private String text;
    
    /** 权限用户 */
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "userId")
    //private User user;
    
    /** 权限角色 */
    @ManyToMany(mappedBy="permissions")
    @Transient
    private List<Role> roles;
}
