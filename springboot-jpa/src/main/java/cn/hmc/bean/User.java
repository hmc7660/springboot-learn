package cn.hmc.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_age")
    private Integer age;

    /**
     * 配置用户到角色的多对多关系
     *      配置多对多的映射关系
     *          1.声明表关系的配置
     *              @ManyToMany(targetEntity = Role.class) //多对多
     *                  targetEntity ： 代表对方的实体类字节码
     *          2.配置中间表（包含两个外键）
     *              @JoinTable
     *                  name:中间表的名称
     *                  joinColumns：配置当前对象在中间表的外键
     *                      name:外键名
     *                      referencedColumnName：参照的主表的主键名
     *                  inverseJoinColumns：配置对方对象在中间表的外键
     */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)    //cascade = CascadeType.ALL  配置级联操作
    @JoinTable(name = "sys_user_role",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
            //inverseJoinColumns,对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

}
