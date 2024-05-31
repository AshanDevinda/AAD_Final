package lk.ijse.aad.helloshoe.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.helloshoe.entity.CustomerEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.DataReadException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.repo.CustomerRepo;
import lk.ijse.aad.helloshoe.dto.CustomerDTO;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    private final Mapping mapper;
    private final CustomerRepo customerRepo;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerCode("C" + UUID.randomUUID().toString().substring(0, 8));
        CustomerEntity customerEntity = mapper.toCustomerEntity(customerDTO);
        if (null == customerDTO.getJoinDateAsALoyaltyCustomer()) {
            customerEntity.setJoinDateAsALoyaltyCustomer(null);
        } else {
            customerEntity.setJoinDateAsALoyaltyCustomer(mapper.StringDateToLocalDate(customerDTO.getJoinDateAsALoyaltyCustomer()));
        }
        customerEntity.setDob(mapper.StringDateToLocalDate(customerDTO.getDob()));
        try {
            return mapper.toCustomerDTO(customerRepo.save(customerEntity));
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, String customerCode) {
        CustomerEntity customerEntity = customerRepo.findById(customerCode).orElseThrow(() -> new NotFountException());
        try {
            customerEntity.setCustomerName(customerDTO.getCustomerName());
            customerEntity.setGender(String.valueOf(customerDTO.getGender()));
            customerEntity.setDob(LocalDate.parse(customerDTO.getDob()));
            customerEntity.setAddress1(customerDTO.getAddress1());
            customerEntity.setAddress2(customerDTO.getAddress2());
            customerEntity.setAddress3(customerDTO.getAddress3());
            customerEntity.setAddress4(customerDTO.getAddress4());
            customerEntity.setAddress5(customerDTO.getAddress5());
            customerEntity.setContact(customerDTO.getContact());
            customerEntity.setEmail(customerDTO.getEmail());

            CustomerDTO updatedCustomerDTO = mapper.toCustomerDTO(customerEntity);
            return updatedCustomerDTO;
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public void removeCustomer(String customerCode) {
        if (!customerRepo.existsById(customerCode)) {
            throw new NotFountException();
        }
        try {
            customerRepo.deleteById(customerCode);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try {
           List<CustomerEntity> customerEntityList = customerRepo.findAll();
            return mapper.toCustomerDTOList(customerEntityList);
        } catch (Exception e) {
            throw new DataReadException();
        }
    }

    @Override
    public void becomeLoyaltyCustomer(String customerCode, LocalDate joinLoyaltyDate) {
        try {
            CustomerEntity customer = customerRepo.findById(customerCode).orElseThrow(() -> new NotFountException());
            customer.setJoinDateAsALoyaltyCustomer(joinLoyaltyDate);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public void addPointAndMarkRecentPurchaseDateAndLevelForCustomer(String customerCode, int newPoints, Timestamp recentPurchaseDate) {
        try {
            CustomerEntity customer = customerRepo.findById(customerCode).orElseThrow(() -> new NotFountException());
            int totalPoints = customer.getTotalPoints() + newPoints;
            customer.setTotalPoints(totalPoints);
            customer.setRecentPurchaseDate(recentPurchaseDate);
            if (totalPoints < 50) {
                customer.setLevel("NEW");
            } else {
                if (totalPoints > 49 && totalPoints < 100) {
                    customer.setLevel("BRONZE");
                } else {
                    if (totalPoints > 99 && totalPoints < 200) {
                        customer.setLevel("SILVER");
                    } else {
                        customer.setLevel("GOLD");
                    }
                }
            }
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }
}
