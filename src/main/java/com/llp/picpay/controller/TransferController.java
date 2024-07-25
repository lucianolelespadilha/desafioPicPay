package com.llp.picpay.controller;

import com.llp.picpay.dto.TransferDto;
import com.llp.picpay.entity.Transfer;
import com.llp.picpay.service.TransferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RestController
public class TransferController {

    private final TransferService transferService;


    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {

        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);


    }
}
