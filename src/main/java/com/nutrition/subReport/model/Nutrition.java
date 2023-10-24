package com.nutrition.subReport.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Nutrition {
    private String nutritionName;
    private int goal;
    private int total;
    private String metric;
}