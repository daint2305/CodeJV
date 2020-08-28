/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.learning.bean.Page;
import com.learning.bean.ResultObj;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.learning.model.Person;
import com.learning.serviceImpl.PersonServiceImpl;
import com.pj.media.util.ResoureUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author thanh
 */
@Controller
@RequestMapping("/thymeleafController")
public class ThymeleafController {

    @Autowired
    public PersonServiceImpl personServiceImpl;

    private String IMAGE_FOLDER = ResoureUtil.getInstance().getProperty("IMAGE_FOLDER");
    private String IMAGE_URL = ResoureUtil.getInstance().getProperty("IMAGE_URL");

    private String searchStartTime;
    private String searchEndTime;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final int pageSize = 4;

    @RequestMapping(value = {"/", "/list", "/search"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model, HttpServletRequest request,
            @RequestParam(value = "searchStartTime", defaultValue = "") String searchStartTime,
            @RequestParam(value = "searchEndTime", defaultValue = "") String searchEndTime,
            @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {

        Date startTime = null;
        Date endTime = null;

        StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();

        url = url.concat("?");
        if (searchStartTime != null && !searchStartTime.trim().isEmpty()) {

            try {
                startTime = this.dateFormat.parse(searchStartTime);
                url = url.concat("&").concat("searchStartTime=").concat(searchStartTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.searchStartTime = searchStartTime;
            model.addAttribute("searchStartTime", searchStartTime);
        }

        if (searchEndTime != null && !searchEndTime.trim().isEmpty()) {
            try {
                endTime = this.dateFormat.parse(searchEndTime);
                url = url.concat("&").concat("searchEndTime=").concat(searchEndTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.searchEndTime = searchEndTime;
            model.addAttribute("searchEndTime", searchEndTime);
        }

        Page objPage = new Page();
        int totalRows = personServiceImpl.countByDate(startTime, endTime);
        int totalPages = totalRows / pageSize;
        if (totalRows % pageSize != 0) {
            totalPages++;
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageIndex);
        objPage.setSearchUrl(url);

        model.addAttribute("pageInfo", objPage);

        List<Person> lst = personServiceImpl.searchByDate(startTime, endTime, pageIndex, this.pageSize);
        model.addAttribute("dataList", lst);

        return "thymeleaf/index";
    }

    @RequestMapping(value = {"/view"}, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String view(Model model, HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "-1") Long id) {

        Person personForm = personServiceImpl.getById(id);
        model.addAttribute("personForm", personForm);
        return "thymeleaf/view";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("personForm", new Person());
        return "thymeleaf/detail";
    }

    @RequestMapping(value = {"/detail"}, method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String detail(Model model, HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "-1") Long id) {

        Person personForm = personServiceImpl.getById(id);
        model.addAttribute("personForm", personForm);

        //luu thong tin person vao session
        request.getSession().setAttribute("currentPerson", personForm);

        return "thymeleaf/detail";
    }

    @ResponseBody
    @RequestMapping(value = {"/save"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String save(Model model, HttpServletRequest request, @ModelAttribute(name = "personForm") Person personForm) {
        ResultObj resultObj = new ResultObj();

        if (personForm.getName() == null || personForm.getName().trim().isEmpty()) {
            resultObj.setStatus(1);
            resultObj.setError("Bạn chưa điền tên");
            return resultObj.toString();
        }

        if (personForm.getId() == null && personForm.getUploadedFile() == null) {
            resultObj.setStatus(1);
            resultObj.setError("Bạn chưa upload file ảnh");
            return resultObj.toString();
        }

        personServiceImpl.addData(personForm);

        if (personForm.getUploadedFile() != null && !personForm.getUploadedFile().isEmpty()) {
            try {
                byte[] bytes = personForm.getUploadedFile().getBytes();

                String newFileName = (personForm.getId()) + "_" + personForm.getUploadedFile().getOriginalFilename();
                Path path = Paths.get(IMAGE_FOLDER + File.separator + newFileName);
                Files.write(path, bytes);
                personForm.setImg(newFileName);
                personServiceImpl.addData(personForm);

            } catch (Exception e) {
                e.printStackTrace();
                resultObj.setStatus(1);
                resultObj.setError("Có lỗi xảy ra");
                return resultObj.toString();
            }
        } else {

            Object per = request.getSession().getAttribute("currentPerson");
            if (per != null && (per instanceof Person)) {
                personForm.setImg(((Person) per).getImg());
                personServiceImpl.addData(personForm);
            }

        }
        request.getSession().removeAttribute("currentPerson");

        return resultObj.toString();
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST,
            produces = "text/html; charset=utf-8")
    @ResponseBody
    public String delete(Model model, HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "-1") Long id) {

        Person p = new Person();
        p.setId(id);
        personServiceImpl.delete(p);

        return "Success";
    }

    @ModelAttribute("IMAGE_FOLDER")
    public String getIMAGE_FOLDER() {
        return IMAGE_FOLDER;
    }

    public void setIMAGE_FOLDER(String IMAGE_FOLDER) {
        this.IMAGE_FOLDER = IMAGE_FOLDER;
    }

    @ModelAttribute("IMAGE_URL")
    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

}
