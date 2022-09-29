package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.AdviceMapper;
import com.kuaiyibu.pojo.Advice;
import com.kuaiyibu.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdviceServicelmpl implements AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    public List<Advice> dySelect(Advice advice) {return adviceMapper.dySelect(advice);
    }

    public List<Advice> selectAll() {
        return adviceMapper.selectAll();
    }

    public Advice getAId(Integer aid) {
        return adviceMapper.getAId(aid);
    }

    public Boolean addAdvice(Advice advice) {
        char[] contents = advice.getAdviceContent().toCharArray();
        Boolean flag = true;
        for (int i = 0;i < advice.getAdviceContent().length(); i++){
            if(contents[i] != ' '){
                flag = false;
            }
        }
        if(flag){
            return false;
        }
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new Date());
        advice.setTime(datetime);
        return adviceMapper.addAdvice(advice) >0;
    }

    public Boolean deleteAdvice(Integer aid) { return adviceMapper.deleteAdvice(aid)>0;
    }




}
