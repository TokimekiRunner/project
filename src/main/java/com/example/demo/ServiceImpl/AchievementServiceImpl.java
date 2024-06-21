package com.example.demo.ServiceImpl;

import com.example.demo.Util.MybatisUtil;
import com.example.demo.dao.Achievementdao;
import com.example.demo.entity.*;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.AchievementService;
import com.example.demo.service.CalService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    private CalService calService;

    @Resource
    private Achievementdao achievementdao;
    @Override
    public int insertAchievement(Achievement achievement){
        return achievementdao.insertAchievement(achievement);
    }
    @Override
    public int insertPerson(Person person){
        return achievementdao.insertPerson(person);
    }

    @Override
    public String getAchievements(){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Achievement> achievementList = achievementdao.getAchievementList();
   //     String personJson = gson.toJson(personList);
  //      System.out.println(personJson);
        String achievementJson = gson.toJson(achievementList);
        System.out.println(achievementJson);
     //   jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();    }




    @Override
    public String getAchievementByPersonname(String name){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbypersonname(name);
        List<Achievement> achievementList = achievementdao.getAchievementListbypersonname(name);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }

    @Override

    public String getAchievementById(int id) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbyid(id);
        List<Achievement> achievementList = achievementdao.getAchievementbyid(id);
        String personJson = gson.toJson(personList);
        System.out.println(personJson);
        String achievementJson = gson.toJson(achievementList);
        System.out.println(achievementJson);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }
    @Override
    public String getAchievementByname(String name){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbyname(name);
        List<Achievement> achievementList = achievementdao.getAchievementListbyname(name);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }

    @Override
    public String getAchievementBytype(String type){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        List<Person> personList = achievementdao.getPersonbytype(type);
        List<Achievement> achievementList = achievementdao.getAchievementListbytype(type);
        String personJson = gson.toJson(personList);
        String achievementJson = gson.toJson(achievementList);
        jsonArray.add(personJson);
        jsonArray.add(achievementJson);
        return jsonArray.toString();
    }

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
                    if(item.getMeeting_level().equals("A")){
                        totalscore += tmp*3;
                        item.setCalscore(tmp*3);
                    }else if (item.getMeeting_level().equals("B")){
                        totalscore += tmp*2;
                        item.setCalscore(tmp*2);
                    } else if (item.getMeeting_level().equals("C")){
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

    @Override
    public List<Rank> calrank(String year){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);

    List<Person> people = mapper.getallperson();
    List<Rank> ranklist = new ArrayList<Rank>();
        for(Person person : people){
            Rank rank = new Rank();
            rank.setPerson_id(person.getPerson_id());
            rank.setPersonname(person.getPersonname());
            Everything everything = new Everything();
            everything.setPerson_id(String.valueOf(person.getPerson_id()));
            everything.setYear(year);
            List<Everything> result41p1y = mapper.dsearch(everything);//这里查询
            result41p1y=calService.calwork(result41p1y);
            rank.setCalscore(calService.caltotal(result41p1y));
            ranklist.add(rank);
        }
        sqlSession.close();
    return ranklist;
    }

    @Override
    public int Insert(Everything everything){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.insert(everything);
        switch (everything.getCategory()){
            case "research":
                mapper.insertresearch(everything);
                break;
            case "award":
                mapper.insertaward(everything);
                break;
            case "patent":
                mapper.insertpatent(everything);
                break;
            case "visit":
                mapper.insertvisit(everything);
                break;

        }
        sqlSession.commit();
        sqlSession.close();
        return 1;
    }

    @Override
    public int Delete(Everything everything){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.delete(everything);
        System.out.println(everything.getCategory());
        System.out.println("hello delete");
        switch (everything.getCategory()){
            case "research":
                mapper.deleteresearch(everything);
                break;
            case "award":
                mapper.deleteaward(everything);
                break;
            case "patent":
                mapper.deletepatent(everything);
                break;
            case "visit":
                mapper.deletevisit(everything);
                break;

        }
        sqlSession.commit();
        sqlSession.close();
        return 1;
    }

    public int InsertPerson(Person person){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.insertperson(person);
        sqlSession.commit();
        sqlSession.close();
        return 1;
    }
    public Person GetPerson(Person person){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Person tmp = mapper.getperson(person);
        sqlSession.commit();
        sqlSession.close();
        return tmp;
    }
    public int DeletePerson(Person person){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.deleteperson(person);
        sqlSession.commit();
        sqlSession.close();
        return 1;
    }

    public int verify(AdmUser admUser){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        String truepassword = mapper.getpassword(admUser);
        sqlSession.close();
        if(truepassword.equals(admUser.getUserpassword())){
            return 1;
        }else{
            return 0;
        }

    }





}
