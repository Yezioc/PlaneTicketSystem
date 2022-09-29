package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.NoticeMapper;
import com.kuaiyibu.pojo.Notice;
import com.kuaiyibu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<Notice> getAll() {
        return noticeMapper.selectAll();
    }

    public List<Notice> dySelect(Notice notice) {return noticeMapper.dySelect(notice);
    }

    public Boolean addNotice(Notice notice) {
        return noticeMapper.addNotice(notice) > 0;
    }

    public Notice getById(Integer nid) {
        return noticeMapper.getById(nid);
    }

    public Boolean updateNotice(Notice notice){
        return noticeMapper.updateNotice(notice)>0;
    }

    public Boolean deleteNotice(Integer nid) {
        return noticeMapper.deleteNotice(nid)>0;
    }

}
