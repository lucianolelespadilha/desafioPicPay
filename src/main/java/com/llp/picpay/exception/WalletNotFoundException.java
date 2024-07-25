package com.llp.picpay.exception;

import com.llp.picpay.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WalletNotFoundException extends PicPayExeption{

    private Long walletId;
    Wallet wallet = new Wallet();

    public WalletNotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet not found");
        pb.setDetail("There is no Wallet with id " + walletId + "." );
        return pb;
    }
}
