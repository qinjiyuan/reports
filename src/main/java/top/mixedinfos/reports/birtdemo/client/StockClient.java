package top.mixedinfos.reports.birtdemo.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "stockClient",url = "${host.stockservice}")
public interface StockClient {
}
