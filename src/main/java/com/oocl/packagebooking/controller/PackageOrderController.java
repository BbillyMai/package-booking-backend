package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.PackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oocl.packagebooking.service.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PackageOrderController {

    @Autowired
    private PackageOrderService packageOrderService;

    @GetMapping("/packageOrders")
    public List<PackageOrder> getAllList(){
        return packageOrderService.getAllList();
    }

    @PostMapping("/packageOrders")
    public ResponseEntity<PackageOrder> addPackageOrder(@RequestBody PackageOrder packageOrder){
        return ResponseEntity.status(HttpStatus.CREATED).body(packageOrderService.addOrder(packageOrder));
    }

    @PutMapping("/packageOrders/{orderId}")
    public ResponseEntity<PackageOrder> confirmOrder(@PathVariable String orderId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(packageOrderService.confirmOrder(orderId));
    }

    @PutMapping(value = "/packageOrders/{orderId}", params = {"bookingTime"})
    public ResponseEntity<PackageOrder> bookingTime(@PathVariable String orderId,@RequestParam String bookingTime){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(packageOrderService.setBookingTime(orderId,bookingTime));
    }
}
