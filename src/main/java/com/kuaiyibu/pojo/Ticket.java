package com.kuaiyibu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Ticket {

    @TableId("tid")
    private Integer tid;

    private String shift;

    private String origin;

    private String end;

    private String date;

    private String startTime;

    private String endTime;

    private Integer price;

}
