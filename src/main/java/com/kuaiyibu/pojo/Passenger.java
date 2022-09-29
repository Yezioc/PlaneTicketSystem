package com.kuaiyibu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Passenger {
    private int pid;
    @TableField("p_name")
    private String psName;
    private String cardType;
    private String idCard;
    private int uid;
}
