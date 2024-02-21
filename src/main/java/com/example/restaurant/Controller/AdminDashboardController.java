package com.example.restaurant.Controller;

import com.example.restaurant.Model.*;
import com.example.restaurant.Service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminDashboardController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @Autowired
    private AreaService areaService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCatService subCatService;
    @Autowired
    private RestaurantService rs;
    @Autowired
    private OfferService offerService;
    @Autowired
    private ComplaintService cs;
    @GetMapping("/admin-dashboard/city")
    public String adminCity(Model model,
                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<City> citiesPage = cityService.getCities(pageNo, pageSize);
        int totalPages = citiesPage.getTotalPages();
        List<City> cities = citiesPage.getContent();
        model.addAttribute("city", cities);
        model.addAttribute("totalPages", totalPages);
        return "city";
    }
    @GetMapping("/admin-dashboard/add-city")
    public String adminAddCity() {
        return "add-city";
    }
    @PostMapping(value = "/admin-dashboard/add-city/add")
    public String create(@RequestParam String cityName,@RequestParam String cityDesc){
        City u = new City(cityName, cityDesc);
        cityService.saveUser(u);
        return "redirect:/admin-dashboard/city";
    }
    @GetMapping("/admin-dashboard/city/{cityid}/delete")
    public String deleteCity(@PathVariable Integer cityid) {
        cityService.deletecitybyid(cityid);
        return "redirect:/admin-dashboard/city";
    }
    @GetMapping("/admin-dashboard/city/{cityid}/edit")
    public String editCity(@PathVariable Integer cityid,Model model) {
        Optional<City> x = cityService.getcitybyid(cityid);
        if(x.isPresent())model.addAttribute("city",x.get());
        else {return "redirect:/admin-dashboard/city";}
        return "edit-city";
    }
    @PostMapping("/admin-dashboard/edit-city/{cityid}/add")
    public String updateUser(@PathVariable Integer cityid,
                             @RequestParam String cityName,
                             @RequestParam String cityDesc
    ) throws IOException {
        Optional<City> u = cityService.getcitybyid(cityid);
        City city;
        if (u.isPresent()){
            city = u.get();
        }
        else {return "redirect:/admin-dashboard/city";}
        if(!cityName.equals(city.getName()))city.setName(cityName);
        if(!cityDesc.equals(city.getDescription()))city.setDescription(cityDesc);
        cityService.saveUser(city);
        return "redirect:/admin-dashboard/city";
    }

    @GetMapping("/admin-dashboard/area")
    public String managearea(Model model,
                             @RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Area> citiesPage = areaService.getarea(pageNo, pageSize);
        int totalPages = citiesPage.getTotalPages();
        List<Area> cities = citiesPage.getContent();
        model.addAttribute("city", cities);
        model.addAttribute("totalPages", totalPages);
        return "area";
    }
    @GetMapping("/admin-dashboard/add-area")
    public String adminAddarea(Model model) {
        List<City> cities = cityService.getCity();
        model.addAttribute("cities", cities);
        return "add-area";
    }
    @PostMapping(value = "/admin-dashboard/add-area/add")
    public String createArea(@RequestParam Integer cityId, @RequestParam String areaName, @RequestParam String areaDesc) {
        Optional<City> optionalCity = cityService.getcitybyid(cityId);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            Area area = new Area(areaName, areaDesc, city);
            areaService.savearea(area);
            return "redirect:/admin-dashboard/area";
        } else {
            return "error";
        }
    }
    @GetMapping("/admin-dashboard/area/{areaid}/delete")
    public String deleteArea(@PathVariable Integer areaid) {
        areaService.deleteareabyid(areaid);
        return "redirect:/admin-dashboard/area";
    }
    @GetMapping("/admin-dashboard/area/{areaid}/edit")
    public String editArea(@PathVariable Integer areaid,Model model) {
        List<City> cities = cityService.getCity();
        model.addAttribute("cities", cities);
        Optional<Area> x = areaService.getareabyid(areaid);
        if(x.isPresent())model.addAttribute("area",x.get());
        else {return "redirect:/admin-dashboard/area";}
        return "edit-area";
    }
    @PostMapping("/admin-dashboard/edit-area/{areaid}/add")
    public String updateArea(@PathVariable Integer areaid,
                             @RequestParam String cityName,
                             @RequestParam Integer cityId,
                             @RequestParam String cityDesc
    ) throws IOException {
        Optional<Area> a = areaService.getareabyid(areaid);
        Optional<City> optionalCity = cityService.getcitybyid(cityId);
        Area area;
        if (a.isPresent()){
            area = a.get();
        }
        else {return "redirect:/admin-dashboard/area";}
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            if(!city.equals(area.getCity()))area.setCity(city);
            if(!cityName.equals(area.getName()))area.setName(cityName);
            if(!cityDesc.equals(area.getDescription()))area.setDescription(cityDesc);
            areaService.savearea(area);
            return "redirect:/admin-dashboard/area";
        }
        return "redirect:/admin-dashboard/area";
    }




    @GetMapping("/admin-dashboard/category-details")
    public String categoryDetails(Model model,
                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Category> categoryPage = categoryService.getCategory(pageNo, pageSize);
        int totalPages = categoryPage.getTotalPages();
        List<Category> cities = categoryPage.getContent();
        model.addAttribute("x", cities);
        model.addAttribute("totalPages", totalPages);
        return "category";
    }
    @GetMapping("/admin-dashboard/add-category")
    public String adminAddcategory() {
        return "add-category";
    }
    @PostMapping(value = "/admin-dashboard/add-category/add")
    public String createcategory(@RequestParam String categoryName,@RequestParam String categoryDesc){
        Category u = new Category(categoryName, categoryDesc);
        categoryService.saveCategory(u);
        return "redirect:/admin-dashboard/category-details";
    }
    @GetMapping("/admin-dashboard/category/{cityid}/delete")
    public String deletecategory(@PathVariable Integer cityid) {
        categoryService.deletecategorybyid(cityid);
        return "redirect:/admin-dashboard/category-details";
    }
    @GetMapping("/admin-dashboard/category/{cityid}/edit")
    public String editcategory(@PathVariable Integer cityid,Model model) {
        Optional<Category> x = categoryService.getcategorybyid(cityid);
        if(x.isPresent())model.addAttribute("category",x.get());
        else {return "redirect:/admin-dashboard/category-details";}
        return "edit-category";
    }
    @PostMapping("/admin-dashboard/edit-category/{cityid}/add")
    public String updatecategory(@PathVariable Integer cityid,
                             @RequestParam String cityName,
                             @RequestParam String cityDesc
    ) throws IOException {
        Optional<Category> u = categoryService.getcategorybyid(cityid);
        Category category;
        if (u.isPresent()){
            category = u.get();
        }
        else {return "redirect:/admin-dashboard/category-details";}
        if(!cityName.equals(category.getName()))category.setName(cityName);
        if(!cityDesc.equals(category.getDescription()))category.setDescription(cityDesc);
        categoryService.saveCategory(category);
        return "redirect:/admin-dashboard/category-details";
    }
    @GetMapping("/admin-dashboard/subcategory")
    public String managesubCat(Model model,
                             @RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SubCategory> subCatPage = subCatService.getSubCategory(pageNo, pageSize);
        int totalPages = subCatPage.getTotalPages();
        List<SubCategory> cities = subCatPage.getContent();
        model.addAttribute("subcategory", cities);
        model.addAttribute("totalPages", totalPages);
        return "subcategory";
    }
    @GetMapping("/admin-dashboard/add-subcategory")
    public String adminAddsubCat(Model model) {
        List<Category> category = categoryService.getCategory();
        model.addAttribute("category", category);
        return "add-subcategory";
    }
    @PostMapping(value = "/admin-dashboard/add-subcategory/add")
    public String createsubCat(@RequestParam Integer categoryId, @RequestParam String subCatName, @RequestParam String subCatDesc) {
        Optional<Category> optionalCity = categoryService.getcategorybyid(categoryId);
        if (optionalCity.isPresent()) {
            Category category = optionalCity.get();
            SubCategory subCat = new SubCategory(subCatName, subCatDesc, category);
            subCatService.savesubCat(subCat);
            return "redirect:/admin-dashboard/subcategory";
        } else {
            return "error";
        }
    }
    @GetMapping("/admin-dashboard/subcategory/{areaid}/delete")
    public String deletesubCat(@PathVariable Integer areaid) {
        subCatService.deletesucatbyid(areaid);
        return "redirect:/admin-dashboard/subcategory";
    }
    @GetMapping("/admin-dashboard/subcategory/{areaid}/edit")
    public String editsubCat(@PathVariable Integer areaid,Model model) {
        List<Category> category = categoryService.getCategory();
        model.addAttribute("category", category);
        Optional<SubCategory> x = subCatService.getsubCatbyid(areaid);
        if(x.isPresent())model.addAttribute("subcat",x.get());
        else {return "redirect:/admin-dashboard/subcategory";}
        return "edit-subcategory";
    }
    @PostMapping("/admin-dashboard/edit-subcategory/{subcatid}/add")
    public String updatesubCat(@PathVariable Integer subcatid,
                             @RequestParam String cityName,
                             @RequestParam Integer cityId,
                             @RequestParam String cityDesc
    ) throws IOException {
        Optional<SubCategory> a = subCatService.getsubCatbyid(subcatid);
        Optional<Category> optionalCity = categoryService.getcategorybyid(cityId);
        SubCategory subCat;
        if (a.isPresent()){
            subCat = a.get();
        }
        else {return "redirect:/admin-dashboard/subcategory";}
        if (optionalCity.isPresent()) {
            Category city = optionalCity.get();
            if(!city.equals(subCat.getCategory()))subCat.setCategory(city);
            if(!cityName.equals(subCat.getName()))subCat.setName(cityName);
            if(!cityDesc.equals(subCat.getDescription()))subCat.setDescription(cityDesc);
            subCatService.savesubCat(subCat);
            return "redirect:/admin-dashboard/subcategory";
        }
        return "redirect:/admin-dashboard/subcategory";
    }

    @GetMapping("/admin-dashboard/manage-restaurant")
    public String adminmanagerestuarnat(Model model) {
        List<Restaurant> restaurant = rs.getallrestaurant();
        model.addAttribute("restaurant",restaurant);
        return "manage-restaurant";
    }
    @GetMapping("/admin-dashboard/offers")
    public String adminoffers(Model model) {
        List<Offer> p = offerService.getAllOffers();
        model.addAttribute("offer", p);
        return "admin-offer";
    }
    @GetMapping("/admin-dashboard/offers/{offersid}/delete")
    public String deleteoffer(@PathVariable Integer offersid) {
        offerService.deletOfferById(offersid);
        return "redirect:/admin-dashboard/offers";
    }
    @GetMapping("/admin-dashboard/complaints")
    public String admincomplaints(Model model) {
        List<Complaint> restaurant = cs.getAllComplaints();
        model.addAttribute("complaint",restaurant);
        return "admin-complaints";
    }


    @GetMapping("/admin-dashboard/complaints/{id}/download")
    public void download(@PathVariable Integer id,HttpServletResponse response) throws IOException {
        Complaint complaint = cs.getcomplaint(id).orElseThrow();
        InputStream in = new ByteArrayInputStream(complaint.getPdf());
        response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        IOUtils.copy(in,response.getOutputStream());
    }
    @GetMapping("/admin-dashboard/complaints/{id}/done")
    public String agree(@PathVariable Integer id) throws IOException {
        Complaint complaint = cs.getcomplaint(id).orElseThrow();
        complaint.setReply("Solved");
        complaint.setStatus("Solved");
        LocalDateTime complaintDate = LocalDateTime.now().withSecond(0).withNano(0);
        complaint.setReplyDate(complaintDate.toString());
        cs.saveComplaint(complaint);
        return "redirect:/admin-dashboard/complaints";
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
