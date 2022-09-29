package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Notice;
import com.kuaiyibu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/admin/notices")
public class AdNoticeController {

    @Autowired
    private NoticeService noticeService;

//    @GetMapping // 查询操作
//    @PostMapping //添加操作
//    @PutMapping //更新操作
//    @DeleteMapping("/{nid}") //删除操作
//        参数(@PathVariable Integer nid)

    @GetMapping
    public List<Notice> getAll() {
        return noticeService.getAll();
    }

    @GetMapping("/{nid}")
    public Notice getById(@PathVariable Integer nid) {
        return noticeService.getById(nid);
    }

    @PostMapping
    public Boolean addNotice(@RequestBody Notice notice){
        System.out.println("notice:--"+notice);
        return noticeService.addNotice(notice);
    }

    @PutMapping
    public Boolean updateNotice(@RequestBody Notice notice){
        System.out.println("notice:--"+notice);
        return noticeService.updateNotice(notice);
    }

    @DeleteMapping("/{nid}")
    public Boolean deleteNotice(@PathVariable Integer nid){
        System.out.println("nid:---"+nid);
        return noticeService.deleteNotice(nid);
    }
    @GetMapping("/dySelect")
    public List<Notice> dySelect(Notice notice){
        System.out.println(notice);
        return noticeService.dySelect(notice);
    }
}
