package com.devsuperior.dsmeta.services.impl;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    private SaleRepository saleRepository;

    @Autowired
    public SmsServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void sendSms(Long id) {

        Sale sale = saleRepository.findById(id).get();

        String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

        String msg = "The Salesperson " + sale.getSellerName() + " was the number 1 in " + date + " with a total amount of " + sale.getAmount();

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();
    }
}
