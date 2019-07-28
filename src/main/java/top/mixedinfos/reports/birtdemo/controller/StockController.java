package top.mixedinfos.reports.birtdemo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mixedinfos.reports.birtconfig.BirtReportGenerator;
import top.mixedinfos.reports.birtconfig.ReportParameter;
import top.mixedinfos.reports.utils.DataTransformUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;


@Api(description = "报表", tags = "report")
@RestController
@RequestMapping(value = "/report/test")
@Slf4j
public class StockController {

    @Autowired
    private BirtReportGenerator birtReportGenerator;


    @ApiOperation(value = "PDF", notes = "PDF测试", httpMethod = "GET")
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


    @ApiOperation(value = "PDF", notes = "PDF测试", httpMethod = "GET")
    @GetMapping("/saveFile")
    public String getStockFile(@RequestParam("fileType") String fileType){
        ReportParameter rm=new ReportParameter("stock_report",fileType);
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

}
