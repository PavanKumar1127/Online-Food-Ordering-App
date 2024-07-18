package com.pavan.foodie.onlineordering.entity.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    
    private String paymentMethod;
    
    private String transactionId;
    
    private String paymentStatus;

    // Additional fields and methods if necessary
}
