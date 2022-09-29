package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Notice;
import com.kuaiyibu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HASEE
 */
@RestController//处理前端控制器发来的请求，经过service封装获得一个model，并返回给view进行展示
@RequestMapping("/notices")
public class NoticeController {

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

    @GetMapping("/dySelect")
    public List<Notice> dySelect(Notice notice) {
        System.out.println(notice);
        return noticeService.dySelect(notice);
    }

}
