package com.poly.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poly.bean.MyItem;
import com.poly.service.ReportService;

@Controller
@RequestMapping(value = "manages/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "receipt.html", method = RequestMethod.GET)
    public String viewReceipt(ModelMap mm, HttpSession session) {
        Date date = new Date();
        List<MyItem> listItem = reportService.reportReceipt(date, 7);
        System.out.println(listItem.toString());
        mm.put("listReceipt", listItem);
        return "manages/report_receipt";
    }

}
