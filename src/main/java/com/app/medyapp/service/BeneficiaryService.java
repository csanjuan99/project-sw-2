package com.app.medyapp.service;

import com.app.medyapp.model.Beneficiary;
import com.app.medyapp.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    public List<Beneficiary> getAllBeneficiary(int userId) {
        return beneficiaryRepository.findBeneficiaryByUserDni(userId);
    }

    public List<Beneficiary> listBeneficiary() {
        return beneficiaryRepository.findAll();
    }

    public void saveBeneficiary(Beneficiary beneficiary) {
        beneficiaryRepository.save(beneficiary);
    }

    public boolean existsbyBeneficiaryId(int beneficiaryId) {
        return beneficiaryRepository.existsById(beneficiaryId);
    }

    public void deleteByBeneficiaryId(int beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
    }

    public Optional<Beneficiary> getBeneficiary(int beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId);
    }

}
