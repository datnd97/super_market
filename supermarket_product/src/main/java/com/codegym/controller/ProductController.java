package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    private static List<String> listType;
    static{
        listType = new ArrayList<String>();
        listType.add("Hải sản");
        listType.add("Rau củ quả");listType.add("Thịt");
        listType.add("Loại khác");
    }
    @GetMapping("/create-product")
    public ModelAndView showCreateFrom() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("listType",listType);
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/product/create");
            modelAndView.addObject("listType",listType);
            return modelAndView;
        }
        else {
            productService.save(product);
            ModelAndView modelAndView = new ModelAndView("/product/create");
            modelAndView.addObject("product", new Product());
            modelAndView.addObject("listType",listType);
            modelAndView.addObject("mgs","Create success");
            return modelAndView;
        }
    }
    @GetMapping("/products")
    public ModelAndView listProduct(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Product>products;
        if(s.isPresent()) {
            products = productService.findAllByNameContaining(s.get(),pageable);
        }
        else{
            products =productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list1");
        modelAndView.addObject("product",products);
        return modelAndView;
    }
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id)
    {
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product",product);
            modelAndView.addObject("listType",listType);
            modelAndView.addObject("msg"," Edit success");
            return modelAndView;
        }
        else{
            return new ModelAndView("/error");
        }
    }
    @PostMapping("edit-product")
    public ModelAndView updateProduct(@Valid @ModelAttribute Product product,BindingResult bindingResult,Pageable pageable) {
        if(bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("listType",listType);
            return modelAndView;
        }
        else
        {
            productService.save(product);
            Page<Product> products = productService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("/product/list1");
            modelAndView.addObject("product",products);

            return modelAndView;
        }

    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id)
    {
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product",product);
            return modelAndView;
        }
        else{
            return new ModelAndView("/error");
        }
    }
    @PostMapping("/delete-product")
    public String deleteStudent(@ModelAttribute Product product) {
        productService.remove(product.getId());
        return "redirect:products";
    }
}
