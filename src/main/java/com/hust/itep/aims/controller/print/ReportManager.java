package com.hust.itep.aims.controller.print;

import com.hust.itep.aims.entity.print.ParameterReportPayment;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;

public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;


    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("../print/report_pay.jrxml"));
    }

    public void printReportPayment(ParameterReportPayment data) throws JRException {
        Map para = new HashMap();
        para.put("staff", data.getStaff());
        para.put("customer", data.getCustomer());
        para.put("total", data.getTotal());
        para.put("qrcode", data.getQrcode());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
