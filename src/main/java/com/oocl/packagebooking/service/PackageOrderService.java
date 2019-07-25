package com.oocl.packagebooking.service;


import com.oocl.packagebooking.model.PackageOrder;
import com.oocl.packagebooking.repository.PackageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageOrderService {

    @Autowired
    private PackageOrderRepository packageOrderRepository;

    public List<PackageOrder> getAllList() {
        return packageOrderRepository.findAll();
    }
}
