package com.example.Shava.web;

import com.example.Shava.data.Ingredient;
import com.example.Shava.repo.IngeidientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientsByIdConverter implements Converter<String, Ingredient> {

    private IngeidientRepository ingredientRepository;

    @Autowired
    public IngredientsByIdConverter(IngeidientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
