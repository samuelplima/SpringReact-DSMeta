package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService saleService;
    private SmsService smsService;

    @Autowired
    public SaleController(SaleService saleService, SmsService smsService) {
        this.saleService = saleService;
        this.smsService = smsService;
    }


    @GetMapping(value = "/findAll")
    public Page<Sale> findSales(@RequestParam(value= "minDate", defaultValue = " ") String minDate,
                                @RequestParam(value= "maxDate", defaultValue = " ") String maxDate,
                                Pageable pageable) {
        return saleService.findSales(minDate, maxDate,pageable);
    }

    @GetMapping(value = "/{id}/sendSms")
    public void notifySms(@PathVariable Long id){
        smsService.sendSms(id);
    }

}
