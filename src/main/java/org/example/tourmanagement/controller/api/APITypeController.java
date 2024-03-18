package org.example.tourmanagement.controller.api;

import org.example.tourmanagement.model.Type;
import org.example.tourmanagement.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/type")
public class APITypeController {
    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ResponseEntity<Page<Type>> getAllType(Pageable pageable){
        Page<Type> types = typeService.findAll(pageable);
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id){
        Type type = typeService.findById(id).orElse(null);
        if (type != null){
            return new ResponseEntity<>(type, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Type> createTypr(@RequestBody Type type){
        Type types = typeService.save(type);
        return new ResponseEntity<>(types, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type){
        if (!typeService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        type.setId(id);
        Type updateType = typeService.save(type);
        return new ResponseEntity<>(updateType, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id){
        if (!typeService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
