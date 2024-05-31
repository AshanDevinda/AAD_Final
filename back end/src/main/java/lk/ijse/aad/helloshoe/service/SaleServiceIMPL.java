package lk.ijse.aad.helloshoe.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.helloshoe.dto.OrderDTO;
import lk.ijse.aad.helloshoe.dto.OrderItemDTO;
import lk.ijse.aad.helloshoe.dto.SaleDTO;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.repo.OrderItemRepo;
import lk.ijse.aad.helloshoe.repo.OrderRepo;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SaleServiceIMPL implements SaleService{
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final Mapping mapper;
    @Override
    public SaleDTO saveOrder(OrderDTO orderDTO, List<OrderItemDTO> singleOrderDetails) {
       try {
           orderRepo.save(mapper.toOrderDTO(orderDTO));
           orderItemRepo.saveAll(mapper.toSingleOrderItemDTOList(singleOrderDetails));
       }catch (Exception e) {
           throw new DataEffectException();
       }
    }
}
