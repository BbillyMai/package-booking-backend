package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.PackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.packagebooking.service.*;
import java.util.List;


@RestController
public class PackageOrderController {

    @Autowired
    private PackageOrderService packageOrderService;

    @GetMapping("/packageOrders")
    public List<PackageOrder> getAllList(){
        return packageOrderService.getAllList();
    }
}
