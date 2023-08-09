package com.master.minieshop.payment.momo;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.MomoResult;
import com.example.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/orders")
public class MomoController {
	
	@Autowired
    private MomoService momoService;
	
	@GetMapping("momo-pay")
    public ResponseEntity<Void> momoPay(HttpSession session) 
    		throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        Order sessionOrder = (Order) session.getAttribute("order");

        MomoResponse response = momoService.createMomoPayment(sessionOrder);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", response.getPayUrl())
                .build();
    }
    
    @GetMapping("momo-result")
    public String result(Model model, HttpSession session,
                         @RequestParam("partnerCode") String partnerCode,
                         @RequestParam("orderId") String orderId,
                         @RequestParam("requestId") String requestId,
                         @RequestParam("amount") String amount,
                         @RequestParam("orderInfo") String orderInfo,
                         @RequestParam("orderType") String orderType,
                         @RequestParam("transId") String transId,
                         @RequestParam("resultCode") int resultCode,
                         @RequestParam("message") String message,
                         @RequestParam("payType") String payType,
                         @RequestParam("responseTime") String responseTime,
                         @RequestParam("extraData") String extraData,
                         @RequestParam("signature") String signature) {

        MomoResult result = new MomoResult();
        result.setPartnerCode(partnerCode);
        result.setOrderId(orderId);
        result.setRequestId(requestId);
        result.setAmount(amount);
        result.setOrderInfo(orderInfo);
        result.setOrderType(orderType);
        result.setTransId(transId);
        result.setResultCode(resultCode);
        result.setMessage(message);
        result.setPayType(payType);
        result.setResponseTime(responseTime);
        result.setExtraData(extraData);
        result.setSignature(signature);

//        Order order = orderService.getSessionOrder(session);
//        if (order != null) {
//            order.setPaymentMethod(PaymentMethod.Momo);
//            if (result.getResultCode() == 0)
//            {
//                order.setStatus(OrderStatus.Completed);
//                order.setPaymentStatus(PaymentStatus.Paid);
//            }
//            else {
//                order.setStatus(OrderStatus.Failed);
//                order.setPaymentStatus(PaymentStatus.Unpaid);
//            }
//            orderService.save(order);
//        }
//
//        cartService.removeCart(session);
//        orderService.removeSessionOrder(session);

        model.addAttribute("momoResult", result);

        return "cart/paySuccess";
    }
}
