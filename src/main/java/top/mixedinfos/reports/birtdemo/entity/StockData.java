package top.mixedinfos.reports.birtdemo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Domain model for stock data
 * @author Lars Vogel
 */

@Getter@Setter
public class StockData {
    private String name;
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
}
