package com.example.mono;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Order;
import com.example.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MomoService {
	@Autowired
    private Environment env;
	
	@Autowired
	OrderService service;
	
	@Autowired
    private RestTemplate restTemplate;
	
	public MomoResponse createMomoPayment(Order order) 
			throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        
		String endPoint = env.getProperty("payment.momo.endpoint");
        String ipnUrl = env.getProperty("payment.momo.ipnUrl");
        
        
        String partnerCode = env.getProperty("payment.momo.partner-code");
        String accessKey = env.getProperty("payment.momo.access-key");
        String secretKey = env.getProperty("payment.momo.secret-key");
        
        String requestType = "captureWallet";
        
        String redirectUrl = "http://localhost:8080/orders/momo-result";
        
        String orderInfo = "Khách hàng: " + service.findById(order.getId()).getAccount().getFullname() + " thanh toán";
        String amount = String.valueOf(Math.round(service.findById(order.getId()).getOrderDetails().stream().map(x -> x.getPrice() * x.getQuantity()).reduce(0.0, (a, b) -> a + b))).replaceAll("[^\\d]", ""); // Xóa dấu phẩy
        String orderId = order.getId().toString();
        String requestId = java.util.UUID.randomUUID().toString();
        String extraData = "";
        
        List<MomoItem> items = 
        		order.getOrderDetails().stream()
        		.map(
        				x -> new MomoItem(x.getProduct().getId().toString(), x.getProduct().getName())
        		)
        		.toList();

        String rawHash = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + ipnUrl +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + redirectUrl +
                "&requestId=" + requestId +
                "&requestType=" + requestType;

        MomoSecurity crypto = new MomoSecurity();
        // Sign signature SHA256
        String signature = crypto.signSHA256(rawHash, secretKey);

        MomoRequest momoMessage = new MomoRequest();
        momoMessage.setPartnerCode(partnerCode);
        momoMessage.setPartnerName("MinieShop");
        momoMessage.setStoreId("MinieShop");
        momoMessage.setRequestId(requestId);
        momoMessage.setAmount(amount);
        momoMessage.setOrderId(orderId);
        momoMessage.setOrderInfo(orderInfo);
        momoMessage.setRedirectUrl(redirectUrl);
        momoMessage.setIpnUrl(ipnUrl);
        momoMessage.setLang("vi");
        momoMessage.setExtraData(extraData);
        momoMessage.setRequestType(requestType);
        momoMessage.setItems(items);
        momoMessage.setSignature(signature);

        String result = restTemplate.postForObject(endPoint, 
        		momoMessage, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(result, MomoResponse.class);
    }
}
