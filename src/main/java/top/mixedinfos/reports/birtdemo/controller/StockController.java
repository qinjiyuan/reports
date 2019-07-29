package top.mixedinfos.reports.birtdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mixedinfos.reports.birtconfig.BirtReportGenerator;
import top.mixedinfos.reports.birtconfig.ReportParameter;
import top.mixedinfos.reports.utils.DataTransformUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
@RequestMapping(value = "/report/test")
@Slf4j
public class StockController {

    @Autowired
    private BirtReportGenerator birtReportGenerator;


    @GetMapping("/stockPdf")
    public String getStockPdf(){
        ReportParameter rm=new ReportParameter("stock_report","PDF");
        String base64 = "";
        try {
            ByteArrayOutputStream baos = birtReportGenerator.generate(rm);
            byte [] bytes = baos.toByteArray();
            base64 = DataTransformUtils.byteToBase64(bytes);
            baos.close();
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }

        return  base64 ;
    }


    @GetMapping("/saveFile")
    public String getStockFile(@RequestParam("fileType") String fileType,@RequestParam("names") String names){
        ReportParameter rm=new ReportParameter("stock_report",fileType);
        rm.setParameter("names",names);
        rm.setParameter("urls","https://dpic.tiankong.com/7d/dc/QJ8166545612.jpg?x-oss-process=style/670ws");
        String base64 = "helo";
        try {
            ByteArrayOutputStream baos = birtReportGenerator.generate(rm);
            FileOutputStream fops = new FileOutputStream("E:/test/stock_report"+System.currentTimeMillis()+"."+fileType);
            fops.write(baos.toByteArray());
            fops.close();
            baos.close();
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }

        return  base64 ;
    }

    public static void main(String[]args) throws IOException {
        File file = new File("E:\\test\\1.jpg");

        String base64 = Base64Utils.encodeToString(FileUtils.readFileToByteArray(file));
        System.out.println(base64);
    }

}
