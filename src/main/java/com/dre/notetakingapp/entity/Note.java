package com.dre.notetakingapp.entity;

import lombok.*;

import java.io.Serializable;
@ToString
@Data
@AllArgsConstructor
public class Note implements Serializable {
    private Long id;
    private String title;
    private String content;
}
