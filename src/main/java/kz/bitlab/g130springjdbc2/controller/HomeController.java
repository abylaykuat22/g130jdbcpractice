package kz.bitlab.g130springjdbc2.controller;

import kz.bitlab.g130springjdbc2.entity.Brand;
import kz.bitlab.g130springjdbc2.entity.Item;
import kz.bitlab.g130springjdbc2.service.BrandService;
import kz.bitlab.g130springjdbc2.service.ItemService;
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
    private final ItemService itemService;

    @GetMapping("/brands")
    public String home(Model model) throws SQLException {
        List<Brand> brands = brandService.getBrands();
        model.addAttribute("brands", brands);
        return "brands";
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

    @PostMapping("/brands/edit")
    public String editBrand(Brand brand) throws SQLException {
        brandService.updateBrand(brand);
        return "redirect:/";
    }

    @PostMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable Long id) throws SQLException {
        brandService.deleteBrandById(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String items(Model model) throws SQLException {
        List<Item> items = itemService.getAllItems();
        List<Brand> brands = brandService.getBrands();
        model.addAttribute("tovary", items);
        model.addAttribute("brandy", brands);
        return "home";
    }

    @PostMapping("/items/add")
    public String addItem(Item item) {
        itemService.addItem(item);
        return "redirect:/";
    }

    @GetMapping("/items/{id}")
    public String itemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("tovar", item);
        return "itemDetails";
    }

    @PostMapping("/items/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/";
    }

    @PostMapping("/items/edit")
    public String editItem(Item item) {
        itemService.editItem(item);
        return "redirect:/";
    }
}
