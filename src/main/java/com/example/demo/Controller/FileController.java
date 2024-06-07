package com.example.demo.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.Util.ExcelUtils;
import com.example.demo.entity.AchievementDO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Achievement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class FileController
{
    jdbcController jdbc = new jdbcController();
//    @RequestMapping("/download")
//    public void testExcel(HttpServletResponse response) throws IOException {
//        List<AchievementDO> achievementDOList = new ArrayList<>();
// //       List<Achievement> achievementList = jdbc.getTmpachievements();
////        for (Achievement achievement : achievementList) {
////            AchievementDO achievementDO = new AchievementDO();
////            achievementDO.setId(achievement.getId());
////            achievementDO.setName(achievement.getName());
////            achievementDO.setCategory(achievement.getCategory());
////            achievementDO.setYear(String.valueOf(achievement.getYear()));
////            achievementDO.setPersonId(achievement.getPersonId());
////            achievementDO.setLevel(achievement.getLevel());
////            achievementDOList.add(achievementDO);
////        }
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition","attachment;filename="+"test.xlsx");//或者文件名后缀为xlsx
//        ExcelUtils.writeExcel(response,achievementDOList);
//        System.out.println("hello");
//    }


}
