package com.kuaiyibu.service;

import com.kuaiyibu.pojo.Advice;

import java.util.List;

public interface AdviceService {

    List<Advice> selectAll();

    List<Advice> dySelect(Advice advice);

    Boolean addAdvice(Advice advice);

    Boolean deleteAdvice(Integer aid);

    Advice getAId(Integer aid);
}