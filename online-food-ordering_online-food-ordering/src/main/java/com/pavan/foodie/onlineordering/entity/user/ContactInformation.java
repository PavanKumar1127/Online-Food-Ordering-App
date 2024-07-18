package com.pavan.foodie.onlineordering.entity.user;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformation {
    
    private String phoneNumber;
    private String email;
    private String twitterHandle;
    private String instagramHandle;
    private String website;

    // Additional fields and methods if necessary
}
