package cn.hmc.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    /**
     *配置多对多
     */
//    @ManyToMany(targetEntity = User.class)
////    @JoinTable(name = "sys_user_role",
////            //joinColumns,当前对象在中间表中的外键
////            joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
////            //inverseJoinColumns,对方对象在中间表的外键
////            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}
////    )
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
