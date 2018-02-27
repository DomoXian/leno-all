package com.leno.domain.DO;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>用户</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
@Data
public class UserDO  implements Serializable{

    private Integer userId;

    private String name;

    private String sex;
}
