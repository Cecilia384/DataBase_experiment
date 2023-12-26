package com.zpy.service.impl;

import com.zpy.mapper.OrderMapper;
import com.zpy.pojo.Order;
import com.zpy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Writer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void writeDailyReportToCsv(Writer writer) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        writeReportToCsv(writer, startOfDay, now);
    }

    @Override
    public void writeMonthlyReportToCsv(Writer writer) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.minusMonths(1);
        writeReportToCsv(writer, startOfMonth, now);
    }

    @Override
    public void writeAnnualReportToCsv(Writer writer) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfYear = now.minusYears(1);
        writeReportToCsv(writer, startOfYear, now);
    }

    private void writeReportToCsv(Writer writer, LocalDateTime start, LocalDateTime end) throws IOException {
        List<Order> orders = orderMapper.getOrdersBetween(start.format(dtf), end.format(dtf));

        // CSV Header
        writer.write("Order ID, Customer ID, Order Time, Total Price\n");

        // CSV Data
        for (Order order : orders) {
            // 将 Date 转换为 LocalDateTime
            LocalDateTime orderTime = LocalDateTime.ofInstant(order.getOrderTime().toInstant(), ZoneId.systemDefault());

            writer.write(String.format("%d, %d, %s, %.2f\n",
                    order.getId(),
                    order.getCid(), // 替换为您的 Order 类中获取客户ID的方法
                    dtf.format(orderTime), // 使用转换后的 LocalDateTime 对象进行格式化
                    order.getTotal() // 替换为您的 Order 类中获取总价的方法
            ));
        }
    }
}