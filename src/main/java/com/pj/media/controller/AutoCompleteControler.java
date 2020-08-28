/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.google.gson.Gson;
import com.vnpt.media.ottplus.model.Tag;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author PC1
 */
@Controller
@RequestMapping("/autocomplete")
public class AutoCompleteControler {

    Gson gson = new Gson();
    List<Tag> data = new ArrayList<>();
    
    @GetMapping("/")
    public String getHome(Model model) {

        return "autocomplete/index";

    }
    
    @RequestMapping(value = {"/postData"}, method = RequestMethod.POST  , produces = "application/json; charset=utf-8")
    public String postData(Model model,
             @RequestParam(value = "selectedVal", defaultValue = "0") Long selectedVal){
        
        System.out.println("SelectedVal : "+selectedVal);
        
        return "autocomplete/index";
    }

    public AutoCompleteControler() {
        data.add(new Tag(1, "ruby","https://res.cloudinary.com/prestige-gifting/image/fetch/fl_progressive,q_95,e_sharpen:50,w_480/e_saturation:05/https://www.prestigeflowers.co.uk/images/NF1018.jpg"));
        data.add(new Tag(2, "rails","https://cimages.prvd.com/is/image/ProvideCommerce/PF_18_B545_LAY_SHP_V1?$PFCProductImage$"));
        data.add(new Tag(3, "c / c++","https://www.crestwoodflowers.net/gifs/index-flowers.jpg"));
        data.add(new Tag(4, ".net","https://www.eflorist.co.uk/Products/600x700/PS16BQTSP08S.jpg"));
        data.add(new Tag(5, "python","https://images-na.ssl-images-amazon.com/images/I/61NMxvaC6VL._SL1008_.jpg"));
        data.add(new Tag(6, "java","https://assets.eflorist.com/assets/products/PHR_/T18S105A.jpg"));
        data.add(new Tag(7, "javascript","https://img.grouponcdn.com/deal/3TfReBZp8byTvp5ZcPwvEi5AW53Q/3T-1000x600/v1/c700x420.jpg"));
        data.add(new Tag(8, "jscript","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT61CgTFJxedQee9nC_BYUT0VMsXAfV355Ce7wrWLU-Tu-JH6wq"));
    }

    @RequestMapping(value = {"/getTags"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getTags(@RequestParam String tagName) {

        return simulateSearchResult(tagName);

    }

    private String simulateSearchResult(String tagName) {
        
        if(tagName==null || tagName.trim().isEmpty()){
             return  gson.toJson(data);
        }

        List<Tag> result = new ArrayList<>();
        for (Tag tag : data) {
            if (tag.getTagName().contains(tagName)) {
                result.add(tag);
            }
        }

        return  gson.toJson(result);
    }

}
