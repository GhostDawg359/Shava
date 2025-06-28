package com.example.Shava.data;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShavaOrder {
    @NotBlank(message = "is required")
    private String deliveryName;
    @NotBlank(message = "is required")
    private String deliveryStreet;
    @NotBlank(message = "is required")
    private String deliveryCity;
    @NotBlank(message = "is required")
    private String deliveryState;
    @NotBlank(message = "is required")
    private String deliveryZip;
    @CreditCardNumber(message = "not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "must contain exactly 3 digits")
    private String ccCVV;

    private List<Shava> shavas = new ArrayList<>();

    public void addShava(Shava shava){
        this.shavas.add(shava);
    }
}
