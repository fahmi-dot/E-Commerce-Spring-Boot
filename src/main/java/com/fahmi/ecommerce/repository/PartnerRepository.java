package com.fahmi.ecommerce.repository;

import com.fahmi.ecommerce.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, String> {
    boolean existsByIdCardNumber(String idCardNumber);
}
