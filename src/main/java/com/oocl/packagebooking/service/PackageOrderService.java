package com.oocl.packagebooking.service;


import com.oocl.packagebooking.model.PackageOrder;
import com.oocl.packagebooking.repository.PackageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PackageOrderService {

    @Autowired
    private PackageOrderRepository packageOrderRepository;

    public List<PackageOrder> getAllList() {
        return packageOrderRepository.findAll();
    }

    public PackageOrder addOrder(PackageOrder packageOrder){
        packageOrder.setStatus("未取件");
        packageOrder.setBookingTime(null);
        return packageOrderRepository.save(packageOrder);
    }

    public PackageOrder confirmOrder(String id) {
        PackageOrder packageOrder = packageOrderRepository.findById(id).get();
        packageOrder.setStatus("已取件");
        packageOrderRepository.saveAndFlush(packageOrder);
        return packageOrder;
    }

    public PackageOrder setBookingTime(String id, String time) {
        PackageOrder packageOrder = packageOrderRepository.findById(id).get();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-DD-mm HH:mm:ss");
        try {
            packageOrder.setBookingTime(sdf.parse(time));
            packageOrderRepository.saveAndFlush(packageOrder);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return packageOrder;
    }
}
