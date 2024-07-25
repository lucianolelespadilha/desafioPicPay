package com.llp.picpay.service;

import com.llp.picpay.client.NotificationClient;
import com.llp.picpay.entity.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;



    public void sendNotification(Transfer transfer) {


        try {
            logger.info("Sending notification");

            var resp = notificationClient.sendNotification(transfer);

            if (resp.getStatusCode().isError()) {
                logger.error("Error while sending notification, status code is not ok");
            }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }

}
