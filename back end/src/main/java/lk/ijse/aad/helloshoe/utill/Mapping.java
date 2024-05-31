package lk.ijse.aad.helloshoe.utill;

import lk.ijse.aad.helloshoe.dto.*;
import lk.ijse.aad.helloshoe.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;
    private final DateTimeFormatter dateTimeFormatter;

    public LocalDate StringDateToLocalDate(String stringTime) {
        return LocalDate.parse(stringTime, dateTimeFormatter);
    }

    public String convertBase64(String imagaeString) {
        return Base64.getEncoder().encodeToString(imagaeString.getBytes());
    }

    //    customer
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return mapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return mapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customerEntityList) {
        return mapper.map(customerEntityList, List.class);
    }

    public SupplierDTO toSupplierDTO(SupplierEntity supplierEntity) {
        return mapper.map(supplierEntity, SupplierDTO.class);
    }

    public SupplierEntity toSupplierEntity(SupplierDTO supplierDTO) {
        return mapper.map(supplierDTO, SupplierEntity.class);
    }

    public List<SupplierDTO> toSupplierDTOList(List<SupplierEntity> supplierEntityList) {
        return mapper.map(supplierEntityList, List.class);
    }

    //    employee
    public EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
        return mapper.map(employeeDTO, EmployeeEntity.class);
    }

    public EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
        return mapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntityList) {
        return mapper.map(employeeEntityList, List.class);
    }

    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = mapper.map(itemDTO, ItemEntity.class);
        itemEntity.setSize_5(itemDTO.getItemSize().getOrDefault("size_5",0));
        itemEntity.setSize_6(itemDTO.getItemSize().getOrDefault("size_6",0));
        itemEntity.setSize_7(itemDTO.getItemSize().getOrDefault("size_7",0));
        itemEntity.setSize_8(itemDTO.getItemSize().getOrDefault("size_8",0));
        itemEntity.setSize_9(itemDTO.getItemSize().getOrDefault("size_9",0));
        itemEntity.setSize_10(itemDTO.getItemSize().getOrDefault("size_10",0));
        itemEntity.setSize_11(itemDTO.getItemSize().getOrDefault("size_11",0));
        return itemEntity;
    }

    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        return mapper.map(itemEntity, ItemDTO.class);
    }

    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntityList) {
        return mapper.map(itemEntityList, List.class);
    }

    public OrderEntity toOrderDTO(OrderDTO orderDTO) {
        return mapper.map(orderDTO,OrderEntity.class);
    }

    public List<OrderItemEntity> toSingleOrderItemDTOList(List<OrderItemDTO> singleOrderDetails) {
        return mapper.map(singleOrderDetails,List.class);
    }

//    public ItemSizeEntity toItemSizeEntity(Object itemSize) {
//        return mapper.map(itemSize,ItemSizeEntity.class);
//    }
}
