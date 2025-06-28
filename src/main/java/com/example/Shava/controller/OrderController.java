package com.example.Shava.controller;
import com.example.Shava.data.ShavaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order") // Assuming you want to keep the order in session
@Slf4j
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        // Ensure that a ShavaOrder object is always available in the model
        // This is crucial if orderForm.html uses th:object="${shavaOrder}"
        if (!model.containsAttribute("shavaOrder")) {
            model.addAttribute("shavaOrder", new ShavaOrder());
        }
        return "orderForm";
    }
}
