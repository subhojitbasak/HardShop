package org.hardware.Controller;

import lombok.extern.slf4j.Slf4j;
import org.hardware.Entity.Order;
import org.hardware.Entity.Products;
import org.hardware.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Products")
@Slf4j
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody Products products) {
        Products product = productService.createProduct(products);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product saved!!");
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Products> findProductById(@PathVariable("id") UUID id) {
        Optional<Products> productById = productService.findProductById(id);
        return productById;
    }

    @GetMapping(value = "/find")
    public List<Products> findAll() {
        List<Products> allProduct = productService.findAllProduct();
        return allProduct;
    }


    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") UUID id) {
        productService.deleteProductById(id);
        return "Product Deleted!!";
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable("id") UUID id, @RequestBody Products updatedProduct) {
        Optional<Products> productById = productService.findProductById(id);
        if (productById.isPresent()) {
            Products existingproducts = productById.get();
            existingproducts.setName(updatedProduct.getName());
            existingproducts.setPrice(updatedProduct.getPrice());
            existingproducts.setDescription(updatedProduct.getDescription());
            existingproducts.setQtyInStocks(updatedProduct.getQtyInStocks());
//            productService.createProduct(existingproducts);
            productService.updateProduct(existingproducts, id);

            return ResponseEntity.status(HttpStatus.OK).body("Product updated!!");
        }
        log.info("Product not Found ");
        log.info("Entered id: " + id + "\n" +
                "Not found");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not Found in the database!!");

    }




    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendMessagetoOrder() {
        String url = "http://localhost:9090/order/endPoint";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = "hello world!";

        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);

        restTemplate.postForObject(url, requestEntity, String.class);


    }

    @RequestMapping(value = "/send2", method = RequestMethod.GET)
    public void sendMessagetoOrderagain() {
        String url = "http://localhost:9090/order/endPoint";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        String message = "hello world!";
        List<Products> message = productService.findAllProduct();

        HttpEntity<List<Products>> requestEntity = new HttpEntity<>(message, headers);

        restTemplate.postForObject(url, requestEntity, String.class);


    }

    @PostMapping("/checkAvaliability")
    public Boolean checkAvaliability(@RequestBody Order order) {
        UUID productId = order.getProductId();
        Optional<Products> products = productService.findProductById(productId);


        if(products.get().getQtyInStocks() - order.getOrderQty() > 0) {
            Products updatedProducts = new Products();
            updatedProducts.setName(products.get().getName());
            updatedProducts.setPrice(products.get().getPrice());
            updatedProducts.setDescription(products.get().getDescription());

            updatedProducts.setQtyInStocks(products.get().getQtyInStocks()-order.getOrderQty());

            productService.updateProduct(updatedProducts,products.get().getId());


            return true;
        }
        return false;

    }
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/sendMessageToB")
    public Mono<String> sendMessageToB() {
        // URL of microservice B endpoint
        String url = "http://localhost:9092/storage/endpoint";

        // Message to send
        String message = "Hello Microservice B!";

        // Create WebClient instance
        WebClient webClient = webClientBuilder.baseUrl(url).build();

        // Make the HTTP POST request to microservice B
        return webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(message))
                .retrieve()
                .bodyToMono(String.class);
    }

//

}
