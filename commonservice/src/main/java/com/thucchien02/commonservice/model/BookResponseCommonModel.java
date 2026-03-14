package com.thucchien02.commonservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseCommonModel {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
