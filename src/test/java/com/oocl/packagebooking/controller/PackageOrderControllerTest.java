package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.PackageOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.oocl.packagebooking.service.*;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackageOrderController.class)
public class PackageOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PackageOrderService packageOrderService;

    @Test
    public void should_find_all_order() throws Exception {
        Date date = new Date();
        PackageOrder packageOrder = new PackageOrder();
        packageOrder.setId("1");
        packageOrder.setBookingTime(date);
        packageOrder.setOwner("xiaoming");
        packageOrder.setPhone("1346");
        packageOrder.setStatus("未取件");

        List<PackageOrder> packageOrders = new ArrayList<>();
        packageOrders.add(packageOrder);
        when(packageOrderService.getAllList()).thenReturn(packageOrders);

        ResultActions resultActions = mockMvc.perform(get("/packageOrders"));

        resultActions.andExpect(status().isOk());

    }
}
