package com.example.restaurant.Controller;

import com.example.restaurant.Model.*;
import com.example.restaurant.Security.ImageUtils;
import com.example.restaurant.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class RestaurantController {

    @Autowired
    private SubCatService subCatService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/restaurant-dashboard/product")
    public String restaurantIndexPage(Model model) {
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        List<Product> p = productService.getallproductbyRestaurantId(restaurant.getId());
        System.out.println(p);
        model.addAttribute("product", p);
        return "product";
    }
    @GetMapping("/restaurant-dashboard/product/add-product")
    public String adminProduct(Model model){
        List<Category> cat = categoryService.getCategory();
        model.addAttribute("category", cat);
        List<SubCategory> subcat = subCatService.getSubCategory();
        model.addAttribute("subcat", subcat);

        return "add-product";
    }
    @PostMapping(value = "/restaurant-dashboard/product/add-product/add")
    public String createcategory(@RequestParam Integer category,
                                 @RequestParam Integer subcat,
                                 @RequestParam Integer productPrice,
                                 @RequestParam String ProductName,
                                 @RequestParam String productDesc,
                                 @RequestParam MultipartFile photo){
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        Category categoryModel = categoryService.getcategorybyid(category).orElseThrow();
        SubCategory subcatModel = subCatService.getsubCatbyid(subcat).orElseThrow();
        System.out.println(categoryModel + " "+subcatModel);
        Product u = new Product(ProductName, productPrice, photo, productDesc,categoryModel, subcatModel, restaurant);
        productService.saveProduct(u);
        return "redirect:/restaurant-dashboard/product";
    }
    @GetMapping("/restaurant-dashboard/product/{productid}/delete")
    public String deleteproduct(@PathVariable Integer productid) {

        productService.deleteproductbyid(productid);
        return "redirect:/restaurant-dashboard/product";
    }
    @GetMapping("/restaurant-dashboard/product/{productid}/edit")
    public String editproduct(@PathVariable Integer productid,Model model) {
        Optional<Product> x = productService.getproductbyid(productid);
        List<Category> cat = categoryService.getCategory();
        model.addAttribute("category", cat);
        List<SubCategory> subcat = subCatService.getSubCategory();
        model.addAttribute("subcat", subcat);
        if(x.isPresent())model.addAttribute("product",x.get());
        else {return "redirect:/restaurant-dashboard/product/edit";}
        return "edit-product";
    }

    @PostMapping(value = "/restaurant-dashboard/product/{productid}/edit/add")
    public String editProduct(@PathVariable Integer productid,@RequestParam Integer category,
                                 @RequestParam Integer subcat,
                                 @RequestParam Integer productPrice,
                                 @RequestParam String ProductName,
                                 @RequestParam String productDesc,
                                 @RequestParam MultipartFile photo) throws IOException {
        Product p = productService.getproductbyid(productid).orElseThrow();
        Category categoryModel = categoryService.getcategorybyid(category).orElseThrow();
        SubCategory subcatModel = subCatService.getsubCatbyid(subcat).orElseThrow();
        Product product=p;
        if(!photo.isEmpty()){
            product.setPhoto(ImageUtils.compressImage(photo.getBytes()));
        }
        if(!ProductName.equals(product.getName()))product.setName(ProductName);
        if(!productDesc.equals(product.getDescription()))product.setDescription(productDesc);
        if(!productPrice.equals(product.getPrice()))product.setPrice(productPrice);
        if(!categoryModel.equals(product.getCategory()))product.setCategory(categoryModel);
        if(!subcatModel.equals(product.getSubCategory()))product.setSubCategory(subcatModel);
        productService.saveProduct(p);
        return "redirect:/restaurant-dashboard/product";
    }


    @GetMapping("/restaurant-dashboard/manage-offers")
    public String restaurantOffers(Model model) {
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        List<Offer> p = offerService.getAllOffersByRestaurant(Integer.valueOf(restaurant.getId()));
        model.addAttribute("offer", p);
        return "offers";
    }
    @GetMapping("/restaurant-dashboard/manage-offers/add-offers")
    public String adminOffers(Model model){
        List<Category> cat = categoryService.getCategory();
        model.addAttribute("category", cat);
        List<SubCategory> subcat = subCatService.getSubCategory();
        model.addAttribute("subcat", subcat);

        return "add-offer";
    }
    @PostMapping(value = "/restaurant-dashboard/manage-offers/add-offers/add")
    public String createOffers(@RequestParam Integer category,
                                 @RequestParam Integer subcat,
                                 @RequestParam Integer offerDiscount,
                                 @RequestParam String offerName,
                                 @RequestParam String offerDesc,
                                 @RequestParam String startTime,@RequestParam String endTime){
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        Category categoryModel = categoryService.getcategorybyid(category).orElseThrow();
        SubCategory subcatModel = subCatService.getsubCatbyid(subcat).orElseThrow();
        Offer u = new Offer(offerName, offerDiscount, startTime, endTime, offerDesc,categoryModel, subcatModel, restaurant);
        offerService.saveOffer(u);
        return "redirect:/restaurant-dashboard/manage-offers";
    }
    @GetMapping("/restaurant-dashboard/manage-complaints")
    public String restaurantComplain(Model model) {
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        List<Complaint> p = complaintService.getAllComplaintsByRestaurant(Integer.valueOf(restaurant.getId()));
        model.addAttribute("complaint", p);
        System.out.println(p);
        return "complaints";
    }
    @GetMapping("/restaurant-dashboard/manage-complaints/add-complaints")
    public String adminComplain(){
        return "add-complaint";
    }
    @PostMapping(value = "/restaurant-dashboard/manage-complaints/add-complaints/add")
    public String createComplain(@RequestParam String complaintSubject,
                                 @RequestParam String complaintDesc,
                                 @RequestParam MultipartFile pdf) {
        String username = getLoggedInUserDetails().getUsername();
        Restaurant restaurant = restaurantService.getuserbyemail(username).orElseThrow();
        LocalDateTime complaintDate = LocalDateTime.now().withSecond(0).withNano(0);
        try{
            Complaint u = new Complaint(complaintSubject, complaintDesc, restaurant, complaintDate.toString(), "PENDING", pdf.getBytes());
            complaintService.saveComplaint(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/restaurant-dashboard/manage-complaints";
    }

    @GetMapping("/restaurant-dashboard/manage-orders")
    public String dash(){
        return "restaurant-dashboard";
    }

    @GetMapping("/admin-dashboard/add-product/fetchSubCategoryByCategory")
    public ResponseEntity<List<SubCategory>> getSubCategoryByCategory(@RequestParam int categoryId){
        return new ResponseEntity<>(subCatService.getSubCategoryByCategoryiD(categoryId), HttpStatus.OK);
    }
    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }



}
