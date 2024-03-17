package org.example.tourmanagement.controller;

import org.example.tourmanagement.model.Tour;
import org.example.tourmanagement.model.Type;
import org.example.tourmanagement.service.ITourService;
import org.example.tourmanagement.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {
    @Autowired
    private ITourService iTourService;
    @Autowired
    private ITypeService iTypeService;

    @ModelAttribute("types")
    public Iterable<Type> listTypes(){
        return iTypeService.findAll();
    }

    @GetMapping
    public ModelAndView listTour(){
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = iTourService.findAll();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateTour(){
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tours", new Tour());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createTour(@ModelAttribute("tour") Tour tour){
        iTourService.save(tour);
        return "redirect:/tours";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<Tour> tourOptional = iTourService.findById(id);
        if (tourOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/tour/edit");
            modelAndView.addObject("tours", tourOptional.get());
            return modelAndView;
        }
        return new ModelAndView("error");
    }
    @PostMapping("/edit/{id}")
    public String editTour(@ModelAttribute Tour tour){
        iTourService.save(tour);
        return "redirect:/tours";
    }
    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id){
        Optional<Tour> tourOptional = iTourService.findById(id);
        if (tourOptional.isPresent()){
            iTourService.remove(id);
            return "redirect:/tours";
        }
        return "redirect:/error";
    }
    @GetMapping("/sortP")
    public ModelAndView sortByPrice(){
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = iTourService.sortAllByPriceOrderByPriceAsc();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }
    @GetMapping("/sortT")
    public ModelAndView sortByType(){
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = iTourService.sortAllByTypeAsc();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }
}
