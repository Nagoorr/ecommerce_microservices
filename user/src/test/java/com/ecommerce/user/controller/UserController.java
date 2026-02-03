//package com.ecommerce.user.controller;
//
//import com.ecommerce.user.entity.User;
//import com.ecommerce.user.enums.UserRole;
//import com.ecommerce.user.service.UserService;
//import org.bson.types.ObjectId;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//@WebMvcTest(UserController.class)
//public class UserController {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockitoBean
//    public UserService userService;
//
//    @Mock
//    User user;
//
//    @Test
//    public void testAddUser() throws Exception {
//        user.setUserRole(UserRole.BUYER);
//        user.setId(ObjectId.get());
//        user.setEmail("email");
//      MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("")).andExpect(HttpStatus.ACCEPTED)
//                .andReturn();
//       Assert.equals result.getResponse().getContentAsString();
//    }
//
//}
