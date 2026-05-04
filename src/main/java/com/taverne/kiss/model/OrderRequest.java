package com.taverne.kiss.model;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<Item> items;
}
