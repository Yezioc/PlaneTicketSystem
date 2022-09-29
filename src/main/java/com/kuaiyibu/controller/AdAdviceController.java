package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Advice;
import com.kuaiyibu.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/admin/advices")
public class AdAdviceController {

    @Autowired
    private AdviceService adviceService;
    @GetMapping
    public List<Advice> getAll() {
        System.out.println(adviceService.selectAll());
        return adviceService.selectAll();
    }

    @GetMapping("/{aid}")
    public Advice getAId(@PathVariable Integer aid) {
        return adviceService.getAId(aid);
    }

    @PostMapping("/add")
    public Boolean addAdvice(@RequestBody Advice advice) {
        System.out.println("advice:---" + advice);
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        advice.setTime(datetime);
        return adviceService.addAdvice(advice);
    }


    /*用户删除操作，下面路径delete可以不加，后面的{aid}，参数上的注解表示在路径上获取数据*/
    @DeleteMapping("/delete/{aid}")
    public Boolean deleteAdvice(@PathVariable Integer aid) {
        System.out.println("aid:---" + aid);
        return adviceService.deleteAdvice(aid);
    }

    @GetMapping("/dySelect")
    public List<Advice> dySelect(Advice advice) {
        System.out.println("-----------------------------");
        System.out.println(advice);
        return adviceService.dySelect(advice);
    }
}



    /**
     * 测试1 默认jackson形式
     * @return User对象
     */
 /*   @RequestMapping("/demo")
    public Advice json() {
        Advice advice = new Advice();
        advice.setAdvice_type("zhangsan");
        advice.setAdvice_content("123");
        return adviceService.checkLogin(advice);
    }

}*/
