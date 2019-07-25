package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.PackageOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.oocl.packagebooking.service.*;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Test
    public void should_save_packageOrder() throws Exception {
        Date date = new Date();
        PackageOrder packageOrder = new PackageOrder();
        packageOrder.setId("1");
        packageOrder.setBookingTime(date);
        packageOrder.setOwner("xiaoming");
        packageOrder.setPhone("1346");
        packageOrder.setStatus("未取件");

        when(packageOrderService.addOrder(packageOrder)).thenReturn(packageOrder);
        ResultActions resultActions = mockMvc.perform(post("/packageOrders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(packageOrder)));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void should_confirm_order() throws Exception {

        Date date = new Date();
        PackageOrder packageOrder = new PackageOrder();
        packageOrder.setId("1");
        packageOrder.setBookingTime(date);
        packageOrder.setOwner("xiaoming");
        packageOrder.setPhone("1346");
        packageOrder.setStatus("未取件");

        when(packageOrderService.addOrder(any(PackageOrder.class))).thenReturn(packageOrder);

        ResultActions resultActions = mockMvc.perform(put("/packageOrders/{orderId}",packageOrder.getId()));

        resultActions.andExpect(status().isAccepted());
    }

    @Test
    public void should_set_order_booking_time() throws Exception {

        Date date = new Date();
        PackageOrder packageOrder = new PackageOrder();
        packageOrder.setId("1");
        packageOrder.setBookingTime(date);
        packageOrder.setOwner("xiaoming");
        packageOrder.setPhone("1346");
        packageOrder.setStatus("未取件");

        when(packageOrderService.setBookingTime(anyString(),anyLong())).thenReturn(packageOrder);

        ResultActions resultActions = mockMvc.perform(put("/packageOrders/{orderId}",packageOrder.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .param("bookingTime",String.valueOf(packageOrder.getBookingTime())));

        resultActions.andExpect(status().isAccepted());
    }
}
