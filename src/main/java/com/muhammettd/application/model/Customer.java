package com.muhammettd.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private String name;

    private String sendingBranch;

    private String phoneNumber;

    private String email;

    public Customer(String id, String name, String sendingBranch, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.sendingBranch = sendingBranch;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
