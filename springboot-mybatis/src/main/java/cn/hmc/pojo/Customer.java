package cn.hmc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer implements Serializable {

    private int id;
    private String name;
    private String telephone;
    private String address;
    private String remark;

}
