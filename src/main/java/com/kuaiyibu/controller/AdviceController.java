package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Advice;
import com.kuaiyibu.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @PostMapping("/add")
    public Boolean addAdvice(@RequestBody Advice advice) {
        return adviceService.addAdvice(advice);
    }

}

