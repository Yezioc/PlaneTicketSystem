package com.kuaiyibu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuaiyibu.mapper.SeatMapper;
import com.kuaiyibu.mapper.TicketMapper;
import com.kuaiyibu.pojo.Ticket;
import com.kuaiyibu.service.TicketService;
import com.kuaiyibu.util.HtoU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author HASEE
 */
@Service
@Transactional
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<Ticket> getByCondition(String origin, String end, String date) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Ticket::getOrigin, origin).eq(Ticket::getEnd, end).eq(Ticket::getDate, date);
        List<Ticket> tickets = ticketMapper.selectList(wrapper);
        return tickets;
    }

    @Override
    public List<Ticket> getByConditionAndOrder(String origin, String end, String date, String prop, String sort) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        prop = HtoU.underscoreName(prop);
        wrapper.eq("origin", origin)
                .eq("end", end)
                .eq("date", date)
                .orderBy(!"null".equals(sort) && sort != null
                        , "ascending".equals(sort)
                        , "process".equals(prop) ? "end_time - start_time" : prop);
        List<Ticket> tickets = ticketMapper.selectList(wrapper);
        return tickets;
    }

    @Override
    public List<Ticket> getCity(String col) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT " + col).orderByAsc(col);
        return ticketMapper.selectList(wrapper);
    }

    @Override
    public IPage<Ticket> getPageByCondition(int currentPage, int pageSize, Ticket ticket) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .like(!ticket.getShift().isEmpty(), Ticket::getShift, ticket.getShift())
                .like(!ticket.getOrigin().isEmpty(), Ticket::getOrigin, ticket.getOrigin())
                .like(!ticket.getEnd().isEmpty(), Ticket::getEnd, ticket.getEnd())
                .like(!ticket.getDate().isEmpty(), Ticket::getDate, ticket.getDate())
                .like(!ticket.getStartTime().isEmpty(), Ticket::getStartTime, ticket.getStartTime())
                .like(!ticket.getEndTime().isEmpty(), Ticket::getEndTime, ticket.getEndTime())
                .like(ticket.getPrice() != null && ticket.getPrice() != 0, Ticket::getPrice, ticket.getPrice());
        IPage<Ticket> page = new Page<>(currentPage, pageSize);
        return ticketMapper.selectPage(page, wrapper);
    }

    @Override
    public Boolean addSeat(Integer tid) {
        try {
            for (int i = 1; i <= 50; i++) {
                if (i < 10) {
                    seatMapper.addSeat(tid, "0" + i + "A");
                    seatMapper.addSeat(tid, "0" + i + "B");
                    seatMapper.addSeat(tid, "0" + i + "C");
                    seatMapper.addSeat(tid, "0" + i + "E");
                    seatMapper.addSeat(tid, "0" + i + "F");
                    seatMapper.addSeat(tid, "0" + i + "G");
                } else {
                    seatMapper.addSeat(tid, i + "A");
                    seatMapper.addSeat(tid, i + "B");
                    seatMapper.addSeat(tid, i + "C");
                    seatMapper.addSeat(tid, i + "E");
                    seatMapper.addSeat(tid, i + "F");
                    seatMapper.addSeat(tid, i + "G");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer add(Ticket ticket) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Ticket::getShift, ticket.getShift());
        List<Ticket> tickets = ticketMapper.selectList(wrapper);
        if (tickets.size() > 0) {
            return 0;
        } else if (ticket.getOrigin().equals(ticket.getEnd())) {
            return 0;
        } else {
            try {
                String[] times1 = ticket.getStartTime().split(":");
                String[] times2 = ticket.getEndTime().split(":");
                int count1 = Integer.parseInt(times1[0]) * 60 + Integer.parseInt(times1[1]);
                int count2 = Integer.parseInt(times2[0]) * 60 + Integer.parseInt(times2[1]);
                if (count1 >= count2) {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            ticketMapper.insert(ticket);
            return ticket.getTid();
        }
    }

    @Override
    public Boolean update(Ticket ticket) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Ticket::getShift, ticket.getShift());
        List<Ticket> tickets = ticketMapper.selectList(wrapper);
        Boolean flag = true;
        for (Ticket ticket1 : tickets) {
            if(ticket.getTid().equals(ticket1.getTid())){
                flag = false;
            }
        }
        if (tickets.size() > 0 && flag) {
            return false;
        } else if (ticket.getOrigin().equals(ticket.getEnd())) {
            return false;
        } else {
            try {
                String[] times1 = ticket.getStartTime().split(":");
                String[] times2 = ticket.getEndTime().split(":");
                int count1 = Integer.parseInt(times1[0]) * 60 + Integer.parseInt(times1[1]);
                int count2 = Integer.parseInt(times2[0]) * 60 + Integer.parseInt(times2[1]);
                if (count1 >= count2) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return updateById(ticket);
    }
}
