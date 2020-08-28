/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.google.gson.Gson;
import com.learning.bean.LineChartModel;
import com.learning.bean.LineObj;
import com.pj.media.util.StudentUtils;
import com.pj.media.util.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author thanhlong
 */
@Controller
public class HomeControllder {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model) {
        return "redirect:/person/list";
    }

    @RequestMapping(value = {"/fragments"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String fragments(Model model) {
        model.addAttribute("data", StudentUtils.buildStudents());
        return "fragments";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {

        Utils.removeUsersInSession(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = {"/chart"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String chart(Model model) {
        Gson gson = new Gson();

        LineChartModel chartModel = new LineChartModel();
        List<String> categories = new ArrayList<>();

        categories.add("Aug");
        categories.add("Sep");
        categories.add("Oct");
        categories.add("Nov");
        categories.add("Dec");
        chartModel.setCategories(categories);

        LineObj lineObj1 = new LineObj();
        lineObj1.setName("Ha Noi");
        List<Double> data1 = new ArrayList<>();

        data1.add(8D);
        data1.add(9.2D);
        data1.add(10D);
        data1.add(11D);
        data1.add(12D);
        lineObj1.setData(data1);
        chartModel.getSeries().add(lineObj1);

        LineObj lineObj2 = new LineObj();
        lineObj2.setName("HCM");
        List<Double> data2 = new ArrayList<>();
        data2.add(11D);
        data2.add(12D);
        data2.add(null);
        data2.add(22D);
        data2.add(30D);
        lineObj2.setData(data2);
        chartModel.getSeries().add(lineObj2);

        model.addAttribute("chartModel", gson.toJson(chartModel));
        return "/chart/index";
    }

}
