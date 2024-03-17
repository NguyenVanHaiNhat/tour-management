package org.example.tourmanagement.controller;


import org.example.tourmanagement.model.Type;
import org.example.tourmanagement.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping
    public ModelAndView listType(@PageableDefault(size = 3) Pageable pageable){
        Page<Type> types = iTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("types", new Type());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createType(@ModelAttribute("type") Type type){
        iTypeService.save(type);
        return "redirect:/types";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<Type> typeOptional = iTypeService.findById(id);
        if (typeOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/type/edit");
            modelAndView.addObject("types", typeOptional.get());
            return modelAndView;
        }
        return new ModelAndView("/error");
    }

    @PostMapping("/edit/{id}")
    public String editType(@ModelAttribute Type type){
        iTypeService.save(type);
        return "redirect:/types";
    }
}
