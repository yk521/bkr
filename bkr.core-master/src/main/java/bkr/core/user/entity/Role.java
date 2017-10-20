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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import lombok.Data;

/**
 * 角色表 model
 * 
 * @author chengd
 */
@Entity
@Table(name = "role")
@Data
public class Role {
    /** 角色Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    /** 角色名 */
    private String name;

    /** 说明 */
    private String memo;

    /** 角色用户 */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    @Transient
    private List<User> userList;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name="role_permission",  
        joinColumns={
            @JoinColumn(name="role_id",referencedColumnName="role_id")  
            },  
            inverseJoinColumns={  
             @JoinColumn(name="permission_id",referencedColumnName="permission_id")
       }  
    )
    private List<Permission> permissions;
}
