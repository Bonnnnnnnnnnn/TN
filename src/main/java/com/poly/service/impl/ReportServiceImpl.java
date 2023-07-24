package com.poly.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.MyItem;
import com.poly.dao.ReportDAO;
import com.poly.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    @Override
    public List<MyItem> reportReceipt(Date date, int limit) {
        return reportDAO.reportReceipt(date, limit);
    }

}
