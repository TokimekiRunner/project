package com.example.demo.ServiceImpl;

import com.example.demo.entity.Everything;
import com.example.demo.service.CalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CalServiceImpl implements CalService {

  @Override
  public List<Everything> calwork(List<Everything> everythings){
    float totalscore = 0;
//        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
    for (Everything item : everythings) {
      int tmp = 0;
      if (item.getLevel().equals("国家级")){
        tmp = 3;
      }else if (item.getLevel().equals("省级")){
        tmp = 2;
      } else if (item.getLevel().equals("校级")){
        tmp = 1;
      }
      System.out.println(item.getCategory());
      switch (item.getCategory()){
        case "research":
          if(item.getMeeting_level().equals("A类期刊")){
            totalscore += tmp*3;
            item.setCalscore(tmp*3);
          }else if (item.getMeeting_level().equals("B类期刊")){
            totalscore += tmp*2;
            item.setCalscore(tmp*2);
          } else if (item.getMeeting_level().equals("C类期刊")){
            totalscore += tmp*1;
            item.setCalscore(tmp*1);

          }
          break;
        case "award":
          if(item.getAward_level().equals("一等奖")){
            totalscore += tmp*3;
            item.setCalscore(tmp*3);
          } else if (item.getAward_level().equals("二等奖")){
            totalscore += tmp*2;
            item.setCalscore(tmp*2);
          }else if (item.getAward_level().equals("三等奖")){
            totalscore += tmp*1;
            item.setCalscore(tmp*1);
          }
          break;
        case "patent":
          totalscore+=tmp*1.5;
          item.setCalscore((float) (tmp*1.5));
          break;
        case "visit":
          int num = Integer.parseInt(item.getPeriod());
          totalscore+=tmp*0.1*num;
          item.setCalscore((float) (tmp*0.1*num));
          break;
      }

    }
    return everythings;
  }


  @Override
  public float caltotal(List<Everything> everythings){
    float totalscore = 0;
    for (Everything item : everythings) {
      totalscore += item.getCalscore();}
    return totalscore;
  }
}
