package com.app.medyapp.repository;

import com.app.medyapp.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

    List<Beneficiary> findByBeneficiaryDni(int beneficiaryDni);

    boolean existsByBeneficiaryDni(int beneficiaryDni);

    @Query(value = "SELECT * FROM beneficiary b WHERE b.user_id = :userId", nativeQuery = true)
    List<Beneficiary> findBeneficiaryByUserDni(int userId);

}
