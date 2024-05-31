package lk.ijse.aad.helloshoe.controller;

import lk.ijse.aad.helloshoe.dto.OrderDTO;
import lk.ijse.aad.helloshoe.dto.OrderItemDTO;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public String healthCheck() {
        return "sale controller";
    }

    @PostMapping("/save")
    public ResponseEntity saveOrderDetail(@RequestBody OrderDTO orderDTO, @RequestBody List<OrderItemDTO> singleOrderDetails) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(saleService.saveOrder(orderDTO,singleOrderDetails));
        }catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
