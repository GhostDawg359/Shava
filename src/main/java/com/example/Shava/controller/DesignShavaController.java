package com.example.Shava.controller;

import com.example.Shava.data.Ingredient;
import com.example.Shava.data.Ingredient.Type;
import com.example.Shava.data.Shava;
import com.example.Shava.data.ShavaOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.Shava.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignShavaController {
    @ModelAttribute
    public void addIngridientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredientList, Type type){
        return ingredientList.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(){
        // Example of throwing ResourceNotFoundException
        // In a real application, this would be based on some condition
        // For demonstration, let's say if a specific parameter is missing or invalid
        // if (someConditionIsMet) {
        //     throw new ResourceNotFoundException("Design form resource not found.");
        // }
        return "design";
    }

    @PostMapping
    public String processShava(@Valid Shava shava, Errors errors, @ModelAttribute ShavaOrder shavaOrder){
        if (errors.hasErrors()){
            return "design";
        }
        shavaOrder.addShava(shava);
        log.info("processing shava: {}", shava);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "order")
    public ShavaOrder order(){
        return new ShavaOrder();
    }

    @ModelAttribute(name = "shava")
    public Shava shava(){
        return new Shava();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Assuming you have an error.html or error.jsp
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
