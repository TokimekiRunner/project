package com.example.demo.Util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.entity.AchievementDO;
import com.example.demo.entity.RankDO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    /**
     *  导出Excel
     * @param response 返回对象
     * @param list  Excel表中的记录
     */
    public static void writeExcel(HttpServletResponse response, List<AchievementDO> list) throws IOException {

        ExcelWriter excelWriter= EasyExcel.write(response.getOutputStream()).build();
//        定义工作表对象
        WriteSheet sheet=EasyExcel.writerSheet(0,"sheet").head(AchievementDO.class).build();
//        向Excel文件中写入数据
        excelWriter.write(list,sheet);
//        关闭输出流
        excelWriter.finish();
    }

    public static void writeRank(HttpServletResponse response, List<RankDO> list) throws IOException {

        ExcelWriter excelWriter= EasyExcel.write(response.getOutputStream()).build();
//        定义工作表对象
        WriteSheet sheet=EasyExcel.writerSheet(0,"sheet").head(RankDO.class).build();
//        向Excel文件中写入数据
        excelWriter.write(list,sheet);
//        关闭输出流
        excelWriter.finish();
    }
}