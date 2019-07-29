package top.mixedinfos.reports.birtdemo.service;

import org.springframework.stereotype.Service;
import top.mixedinfos.reports.birtdemo.entity.StockData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service("stockDataService")
public class StockDataService {

	public List<StockData> getStockValues(String company) {
        // Ignore the company and always return the data
        // A real implementation would of course use the company string
        List<StockData> history = new ArrayList<>();
        // We fake the values, we will return fake value for 01.01.2009 -
        // 31.01.2009
        double begin = 2.5;
        for (int i = 1; i <= 31; i++) {
            Calendar day = Calendar.getInstance();
            day.set(Calendar.HOUR, 0);
            day.set(Calendar.MINUTE, 0);
            day.set(Calendar.SECOND, 0);
            day.set(Calendar.MILLISECOND, 0);
            day.set(Calendar.YEAR, 2009);
            day.set(Calendar.MONTH, 0);
            day.set(Calendar.DAY_OF_MONTH, i);
            StockData data = new StockData();
            data.setOpen(begin);
            double close = Math.round(begin + Math.random() * begin * 0.1);
            data.setClose(close);
            data.setLow(Math.round(Math.min(begin, begin - Math.random() * begin * 0.1)));
            data.setHigh(100);
            data.setVolume(1000 + (int) (Math.random() * 500));
            begin = close;
            data.setDate(day.getTime());
            data.setName(company+i);
            history.add(data);
        }
        return history;
    }


    public String getBaseUrl(String names){
	    return "https://dpic.tiankong.com/7d/dc/QJ8166545612.jpg?x-oss-process=style/670ws";
    }
}
