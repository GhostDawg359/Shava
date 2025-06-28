package com.example.Shava.controller;

import com.example.Shava.data.Ingredient;
import com.example.Shava.data.Shava;
import com.example.Shava.data.ShavaOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignShavaController.class)
public class DesignShavaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attributeExists("wrap"))
                .andExpect(model().attributeExists("protein"))
                .andExpect(model().attributeExists("veggies"))
                .andExpect(model().attributeExists("cheese"))
                .andExpect(model().attributeExists("sause"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("shava"));
    }

    @Test
    public void testModelAttributesContent() throws Exception {
        ModelMap modelMap = Objects.requireNonNull(mockMvc.perform(get("/design"))
                        .andReturn()
                        .getModelAndView())
                .getModelMap();

        // Check wrap ingredients
        List<Ingredient> wraps = (List<Ingredient>) modelMap.get("wrap");
        assertThat(wraps).isNotEmpty();
        assertThat(wraps).allMatch(i -> i.getType() == Ingredient.Type.WRAP);

        // Check protein ingredients
        List<Ingredient> proteins = (List<Ingredient>) modelMap.get("protein");
        assertThat(proteins).isNotEmpty();
        assertThat(proteins).allMatch(i -> i.getType() == Ingredient.Type.PROTEIN);

        // Check veggies ingredients
        List<Ingredient> veggies = (List<Ingredient>) modelMap.get("veggies");
        assertThat(veggies).isNotEmpty();
        assertThat(veggies).allMatch(i -> i.getType() == Ingredient.Type.VEGGIES);

        // Check cheese ingredients
        List<Ingredient> cheeses = (List<Ingredient>) modelMap.get("cheese");
        assertThat(cheeses).isNotEmpty();
        assertThat(cheeses).allMatch(i -> i.getType() == Ingredient.Type.CHEESE);

        // Check sause ingredients
        List<Ingredient> sauces = (List<Ingredient>) modelMap.get("sause");
        assertThat(sauces).isNotEmpty();
        assertThat(sauces).allMatch(i -> i.getType() == Ingredient.Type.SAUCE);

        // Check order and shava objects
        assertThat(modelMap.get("order")).isInstanceOf(ShavaOrder.class);
        assertThat(modelMap.get("shava")).isInstanceOf(Shava.class);
    }
}