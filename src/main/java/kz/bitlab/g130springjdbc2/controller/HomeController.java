package kz.bitlab.g130springjdbc2.controller;

import kz.bitlab.g130springjdbc2.entity.Brand;
import kz.bitlab.g130springjdbc2.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BrandService brandService;

    @GetMapping("/")
    public String home(Model model) throws SQLException {
        List<Brand> brands = brandService.getBrands();
        model.addAttribute("brands", brands);
        return "home";
    }

    @PostMapping("/brands/add")
    public String addBrand(Brand brand) {
        brandService.addBrand(brand);
        return "redirect:/";
    }

    @GetMapping("/brands/{id}")
    public String brandDetails(@PathVariable Long id, Model model) throws SQLException {
         Brand brand = brandService.getBrandById(id);
         model.addAttribute("brand", brand);
         return "brandDetails";
    }
}
