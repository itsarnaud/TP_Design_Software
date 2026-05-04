package com.taverne.yagni.model;

import lombok.Data;
import java.util.List;

@Data
public class MenuItem {
    private String       name;
    private double       price;
    private String       category;
    private List<String> ingredients;
    private String       brewingMethod;
    private String       originRegion;
    private boolean      seasonalSpecial;
    private String       allergenInfo;
}
