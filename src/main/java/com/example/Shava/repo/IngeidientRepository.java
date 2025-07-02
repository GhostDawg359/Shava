package com.example.Shava.repo;

import com.example.Shava.data.Ingredient;

import java.util.Optional;

public interface IngeidientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
