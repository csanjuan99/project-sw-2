package com.app.medyapp.controller;


import com.app.medyapp.dto.BeneficiaryDTO;
import com.app.medyapp.dto.LoggedUserDTO;
import com.app.medyapp.dto.MessageDTO;
import com.app.medyapp.model.Beneficiary;
import com.app.medyapp.model.User;
import com.app.medyapp.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/affiliate/beneficiary")
@CrossOrigin
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping("/create")
    public ResponseEntity<?> createBeneficiary(@Valid @RequestBody BeneficiaryDTO beneficiaryDTO) {

        Beneficiary beneficiary = new Beneficiary(beneficiaryDTO.getBeneficiaryDni(), beneficiaryDTO.getBeneficiaryName(), beneficiaryDTO.getBeneficiaryLastname(), beneficiaryDTO.getUser());
        beneficiaryService.saveBeneficiary(beneficiary);
        return new ResponseEntity<>(new MessageDTO("Beneficiario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Beneficiary>> findAll(@Valid @RequestBody LoggedUserDTO loggedUserDTO) {
        List<Beneficiary> listBeneficiary = this.beneficiaryService.getAllBeneficiary(loggedUserDTO.getUserId());
        return new ResponseEntity<List<Beneficiary>>(listBeneficiary, HttpStatus.OK);
    }

    @GetMapping("/everyone")
    public ResponseEntity<List<Beneficiary>> findAll() {
        List<Beneficiary> listBeneficiary = this.beneficiaryService.listBeneficiary();
        return new ResponseEntity<List<Beneficiary>>(listBeneficiary, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{beneficiary_id}")
    public ResponseEntity<?> deleteById(@PathVariable("beneficiary_id") int beneficiaryId) {
        if (!beneficiaryService.existsbyBeneficiaryId(beneficiaryId))
            return new ResponseEntity(new MessageDTO("No existe el afiliado"), HttpStatus.NOT_FOUND);
        beneficiaryService.deleteByBeneficiaryId(beneficiaryId);
        return new ResponseEntity(new MessageDTO("Afiliado eliminado"), HttpStatus.OK);
    }

    @GetMapping("/read/{beneficiary_id}")
    public ResponseEntity<User> getByDni(@PathVariable("beneficiary_id") int beneficiaryId) {

        if (!beneficiaryService.existsbyBeneficiaryId(beneficiaryId))
            return new ResponseEntity(new MessageDTO("No existe el afiliado"), HttpStatus.NOT_FOUND);

        Beneficiary beneficiary = beneficiaryService.getBeneficiary(beneficiaryId).get();
        return new ResponseEntity(beneficiary, HttpStatus.OK);
    }
}
