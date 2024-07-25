package com.llp.picpay.service;

import com.llp.picpay.client.AuthorizationClient;
import com.llp.picpay.dto.TransferDto;
import com.llp.picpay.entity.Transfer;
import com.llp.picpay.exception.PicPayExeption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;


    public boolean isAuthorized(TransferDto transfer) {

        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PicPayExeption();
        }

        return response.getBody().authorized();
    }
}
