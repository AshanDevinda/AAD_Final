package lk.ijse.aad.helloshoe.service;

import lk.ijse.aad.helloshoe.dto.CustomerDTO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO, String id);
    void removeCustomer(String customerCode);
    List<CustomerDTO> getAllCustomers();
    void becomeLoyaltyCustomer(String customerCode, LocalDate joinLoyaltyDate);
    void addPointAndMarkRecentPurchaseDateAndLevelForCustomer(String customerCode, int newPoints, Timestamp recentPurchaseDate);
}
