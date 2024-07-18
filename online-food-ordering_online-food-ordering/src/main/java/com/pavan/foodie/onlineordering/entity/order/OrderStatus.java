package com.pavan.foodie.onlineordering.entity.order;

import jakarta.persistence.Entity;
import lombok.Data;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    DELIVERED,
    CANCELLED
}
