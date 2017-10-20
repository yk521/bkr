package bkr.core.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户表 model
 * 
 * @author chengd
 */
@Entity
@Table(name = "user")
@Data
public class User {
    /** 用户Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    /** 用户名 */
    private String name;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String mail;

    /** 头像 */
    private String photo;

    /** 用户角色 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    /** 用户权限 */
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    //private List<Permission> permissions;
}
