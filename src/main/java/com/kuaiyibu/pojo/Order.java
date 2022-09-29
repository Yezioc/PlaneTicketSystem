package com.kuaiyibu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Order {

    @TableId("oid")
    private Integer oid;

    private String onum;

    private Integer ouid;

    private Integer opid;

    private Integer otid;

    private Integer osid;

    private Passenger passenger;

    private Ticket ticket;

    private Seat seat;

    private String time;

    private String state;

    private String payState;

    private Integer deleted;

}
