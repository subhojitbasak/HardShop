package org.hardware.Controller;

import lombok.extern.slf4j.Slf4j;
import org.hardware.Entity.Order;
import org.hardware.Entity.Products;
import org.hardware.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate ;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {

        String productAvaliabilityUrl = "http://localhost:9091/Products/checkAvaliability";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Order> requestEntity = new HttpEntity<>(order, headers);

        Boolean productAvaliability = restTemplate.postForObject(productAvaliabilityUrl, requestEntity, Boolean.class);

        if(productAvaliability != null && productAvaliability){
            orderService.saveOrder(order);
//            System.out.println(productUpdate+order.getProductId());
//            restTemplate.postForObject(productUpdate+order.getProductId(),requestEntity,Products.class);
            return ResponseEntity.status(HttpStatus.CREATED).body("your order request saved!!!");
        }
        log.error("Product Not avaliable");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product currenty unavaliable");

    }

    @GetMapping("/find")
    public List<Order> findAllOrder() {
        List<Order> order = orderService.findAllOrder();
        log.debug("Showing all orders , This is a debug level log ");
        return order;
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        log.info("Product has been sucessfully deleted , order id:"+orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order sucessfully deleted!!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order updatedorder) {
        Optional<Order> order = orderService.findOrder(orderId);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setUserId(updatedorder.getUserId());
            existingOrder.setStatus(updatedorder.getStatus());
            existingOrder.setOrderDate(updatedorder.getOrderDate());
            existingOrder.setTotalAmount(updatedorder.getTotalAmount());

            orderService.saveOrder(existingOrder);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Order sucessfully updated!!!");
    }

    @PostMapping("/endPoint2")
    public void receive2(@RequestBody String message) {
        System.out.println("Received message from microservice Product:" + message);

    }
}


    /*Chages for Rest template implementation order service*/

//Todo: who is ordering --> User
    //Todo: what is ordering --> Products


//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    public void sendMessagetoProduct(){
//        String url = "http://localhost:9091/product/endPoint";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);
//
//        restTemplate.postForObject(url, requestEntity, String.class);
//
//
//    }



