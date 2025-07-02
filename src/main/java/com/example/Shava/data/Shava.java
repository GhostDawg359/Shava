package com.example.Shava.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;


import java.util.Date;
import java.util.List;

@Data
public class Shava {
    private long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "Description must be at least 1 ingredient")
    private List<Ingredient> ingredients;
}
