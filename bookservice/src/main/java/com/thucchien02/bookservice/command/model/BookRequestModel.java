package com.thucchien02.bookservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2-30 characters")
    private String name;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 30, message = "Author must be between 2-30 characters")
    private String author;

    private Boolean isReady;
}
