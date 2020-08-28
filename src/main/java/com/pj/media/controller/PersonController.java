/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.learning.bean.Page;
import com.learning.model.Person;
import com.learning.model.TblAccount;
import com.learning.service.LanguageService;
import com.learning.service.PersonService;
import com.learning.serviceImpl.PersonServiceImpl;
import com.learning.validator.PersonValidator;
import com.pj.media.util.Utils;
import com.pj.media.util.ValidateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
//@Controller
@Controller
//@RestControllerEnableWebMvc
@RequestMapping("/person")
//@SessionScope
//@Scope("request")
//@Scope("session")

@PropertySource("classpath:config.properties")
public class PersonController {

    public final Integer pageSize = 2;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Value("${IMAGE_FOLDER}")
    public String IMAGE_FOLDER;

    @Value("${IMAGE_URL}")
    public String IMAGE_URL;

    @Autowired
    //private PersonService personService;
    private PersonServiceImpl personService;

    @Autowired
    private PersonValidator personValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(personValidator);
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String onSntart(Model model,
            @RequestParam(value = "searchName", defaultValue = "") String searchName,
            @RequestParam(value = "searchSex", defaultValue = "") Integer searchSex,
            @RequestParam(value = "searchCountry", defaultValue = "") String searchCountry,
            @RequestParam(value = "searchStartTime", defaultValue = "") String searchStartTime,
            @RequestParam(value = "searchEndTime", defaultValue = "") String searchEndTime,
            @RequestParam(value = "page", defaultValue = "1") Integer pageIndex,
            HttpServletRequest request
    ) {

        StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();

        url = url.concat("?1=1");

        if (searchName != null && !searchName.trim().isEmpty()) {
            url = url.concat("&").concat("searchName=").concat(searchName);
        }
        if (searchSex != null) {
            url = url.concat("&").concat("searchSex=").concat(String.valueOf(searchSex));
        }
        if (searchCountry != null && !searchCountry.trim().isEmpty()) {
            url = url.concat("&").concat("searchCountry=").concat(searchCountry);
        }
        if (searchStartTime != null && !searchStartTime.trim().isEmpty()) {
            url = url.concat("&").concat("searchStartTime=").concat(searchStartTime);
        }
        if (searchEndTime != null && !searchEndTime.trim().isEmpty()) {
            url = url.concat("&").concat("searchEndTime=").concat(searchEndTime);
        }

        Date startTime = null;
        Date endTime = null;

        if (searchStartTime != null && !searchStartTime.trim().isEmpty()) {
            try {
                startTime = this.dateFormat.parse(searchStartTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTime);
                cal.set(Calendar.SECOND, 0);
                startTime = cal.getTime();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (searchEndTime != null && !searchEndTime.trim().isEmpty()) {
            try {
                endTime = this.dateFormat.parse(searchEndTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(endTime);
                cal.set(Calendar.SECOND, 59);
                endTime = cal.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Person> lst = personService.getData(searchName, searchSex, searchCountry, startTime, endTime, pageIndex, this.pageSize);

        Page objPage = new Page();

        int totalRows = personService.count(searchName, searchSex, searchCountry, startTime, endTime);
        int totalPages = totalRows / pageSize;
        if (totalRows % pageSize != 0) {
            totalPages++;
        }

        objPage.setTotalPages(totalPages);
        objPage.setTotalRows(totalRows);
        objPage.setDestPage(pageIndex);
        objPage.setDirection(1);
        objPage.setSearchUrl(url);

        model.addAttribute("pageInfo", objPage);

        model.addAttribute("dataList", lst);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchSex", searchSex);
        model.addAttribute("searchCountry", searchCountry);
        model.addAttribute("searchStartTime", searchStartTime);
        model.addAttribute("searchEndTime", searchEndTime);
        model.addAttribute("IMAGE_URL", IMAGE_URL);

        return "person/index";
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public String save(Model model, @ModelAttribute(name = "personForm") @Validated Person personForm, BindingResult result) {

        if (result.hasErrors()) {
            if (personForm.getId() == null) {
                return "person/create";
            }
            return "person/detail";
        }
        if (personForm.getCreateTime() == null) {
            personForm.setCreateTime(new Date());
        }
        personService.addData(personForm);

        if (personForm.getUploadedFile() != null && !personForm.getUploadedFile().isEmpty()) {
            try {
                byte[] bytes = personForm.getUploadedFile().getBytes();

                String newFileName = (new Date().getTime()) + "_" + personForm.getUploadedFile().getOriginalFilename();
                Path path = Paths.get(IMAGE_FOLDER + File.separator + newFileName);
                Files.write(path, bytes);
                personForm.setImg(newFileName);
                personService.addData(personForm);

            } catch (Exception e) {
                e.printStackTrace();
                if (personForm.getId() == null) {
                    return "person/create";
                }
                return "person/detail";
            }
        }

        return "redirect:/person/list";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST,
            produces = "text/html; charset=utf-8")
    @ResponseBody
    public String delete(Model model, HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "-1") Long id) {

        Person p = new Person();
        p.setId(id);
        personService.delete(p);

        return "Success";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET,
            produces = "text/html; charset=utf-8")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("personForm", new Person());
        return "person/add";
    }

    @RequestMapping(value = {"/detail"}, method = RequestMethod.GET,
            produces = "text/html; charset=utf-8")
    public String detail(Model model, HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "-1") Long id) {

        Person personForm = personService.getById(id);
        model.addAttribute("personForm", personForm);
        model.addAttribute("IMAGE_URL", IMAGE_URL);
        return "person/detail";
    }

    @RequestMapping(value = {"/download"}, method = RequestMethod.GET,
            produces = "text/html; charset=utf-8")
    public String download(Model model, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "url", defaultValue = "") String url) {

        try {
            // get your file as InputStream
            File f = new File(IMAGE_FOLDER + File.separator + url);
            if (!f.exists() || f.isDirectory()) {
                return "redirect:/person/list";
            }

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/force-download");
            response.setHeader("Content-disposition", "attachment;filename="
                    + f.getName());

            InputStream is = new FileInputStream(f);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/person/list";
    }

    @PostConstruct
    public void onStart() {

    }

}
