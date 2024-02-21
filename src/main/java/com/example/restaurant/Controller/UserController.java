package com.example.restaurant.Controller;


import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.City;
import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Model.User;
import com.example.restaurant.Service.AreaService;
import com.example.restaurant.Service.CityService;
import com.example.restaurant.Service.RestaurantService;
import com.example.restaurant.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/login")
    public String loginpage() {
        return "login";
    }
    @GetMapping("/redirect")
    public String re(){
        for(GrantedAuthority a:getLoggedInUserDetails().getAuthorities()){
            if(a.getAuthority().equals("ADMIN"))return "redirect:/admin-dashboard";
            if(a.getAuthority().equals("RESTURANT"))return "redirect:/restaurant-dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin-dashboard")
    public String adminIndexPage() {
        return "admin-dashboard";
    }


    @GetMapping("/restaurant-dashboard")
    public String restaurantIndexPage() {
        return "restaurant-dashboard";
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        List<City> cities = cityService.getCity();
        System.out.println(cities);
        model.addAttribute("cities", cities);
        List<Area> area = areaService.getArea();
        model.addAttribute("area", area);
        return "register";
    }
    @PostMapping("/register/add")
    public String registerpage(@RequestParam String resturantname,
                               @RequestParam String restName,
                               @RequestParam String restPassword,
                               @RequestParam String contactnumber,
                               @RequestParam Integer area,
                               @RequestParam Integer city,
                               @RequestParam String address){
        String role ="RESTURANT";
        User u = new User(resturantname, restName, restPassword,role);
        userService.setuser(u);
        Optional<City> optionalCity = cityService.getcitybyid(city);
        City c = optionalCity.get();
        Optional<Area> areaa = areaService.getareabyid(area);
        Area a = areaa.get();
        Restaurant r =new Restaurant(resturantname,restPassword,restName,address,contactnumber,c,a);
        restaurantService.saverestaurant(r);
        return "redirect:/login";
    }
    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    @GetMapping("/register/fetchAreaByCity")
    public ResponseEntity<List<Area>> getAreaByCity(@RequestParam int cityId){
        System.out.println("Hii"+cityId);
        return new ResponseEntity<>(areaService.getAreaByCityId(cityId), HttpStatus.OK);
    }
}