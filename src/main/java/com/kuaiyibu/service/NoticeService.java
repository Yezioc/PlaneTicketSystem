package com.kuaiyibu.service;

import com.kuaiyibu.pojo.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> getAll();

    List<Notice> dySelect(Notice notice);

    Boolean addNotice(Notice notice);

    Boolean updateNotice(Notice notice);

    Notice getById(Integer nid);

    Boolean deleteNotice(Integer uid);
}
