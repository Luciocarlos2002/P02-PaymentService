package com.microservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Payment {

    @Id
    private String id;
    private String idCredit;
    private Double amount;
    @JsonFormat(pattern="dd-MM-yyyy" , timezone="GMT-05:00")
    private Date dateStart = new Date();
    @JsonFormat(pattern="dd-MM-yyyy" , timezone="GMT-05:00")
    private Date dateLimit;
    private Double commission;
    private String description;

}
