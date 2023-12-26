package com.zpy.service;

import java.io.IOException;
import java.io.Writer;

public interface ReportService {

    void writeDailyReportToCsv(Writer writer) throws IOException;
    void writeMonthlyReportToCsv(Writer writer) throws IOException;
    void writeAnnualReportToCsv(Writer writer) throws IOException;

}