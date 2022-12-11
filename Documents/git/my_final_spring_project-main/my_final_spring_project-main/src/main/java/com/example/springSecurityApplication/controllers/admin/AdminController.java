package com.example.springSecurityApplication.controllers.admin;

import com.example.springSecurityApplication.enumm.Status;
import com.example.springSecurityApplication.models.Image;
import com.example.springSecurityApplication.models.Order;
import com.example.springSecurityApplication.models.Product;
import com.example.springSecurityApplication.repositories.CategoryRepository;
import com.example.springSecurityApplication.repositories.OrderRepository;
import com.example.springSecurityApplication.services.OrderService;
import com.example.springSecurityApplication.services.PersonService;
import com.example.springSecurityApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;
    private final PersonService personService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AdminController(PersonService personService, ProductService productService, OrderService orderService, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.personService = personService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("")
    public String admin(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    // Список заказов
    @GetMapping("/allOrder")
    public String allOrder(Model model) {
        model.addAttribute("allorder", orderService.getAllOrder());
        List<Status> statusList = Arrays.stream(Status.values()).collect(Collectors.toList());
        model.addAttribute("allStatus", statusList);
        return "order/allOrder";
    }

    //Список пользователей
    @GetMapping("/allPerson")
    public String allPerson(Model model) {
        model.addAttribute("allperson", personService.getAllPerson());
        return "person/allPerson";
    }

//    //Редактирование заказов
//    @GetMapping("/order/edit/{id}")
//    public String editOrder(Model model, @PathVariable("id") int id) {
//        model.addAttribute("idorder", orderService.getOrderById(id));
//        return "admin/editOrder";
//    }
//
//    // Метод по редактированию товара
//    @PostMapping("/order/edit/{id}")
//    public String editOrder(@ModelAttribute("idorder") @Valid Order order, BindingResult bindingResult, @PathVariable("id") int id) {
//        if (bindingResult.hasErrors()) {
//            return "admin/editOrder";
//        }
//        orderService.updateOrder(id, order);
//        return "redirect:/admin/allOrder";
//    }
//
//
//@GetMapping("/order/create")
//public String order(){
//
//    List<Order> orderList = orderService.getAllOrder();
//    List<Status> statusList = List.of(Status.values());
//
//    String uuid = UUID.randomUUID().toString();
//        Order newOrder = new Order();
//
//        orderRepository.save(newOrder);
//        orderRepository.deleteOrderByOrderId(order.getId());
//    }
//    return "redirect:/orders";
//}
//
//    @GetMapping("/orders")
//    public String ordersUser(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        List<Order> orderList = orderRepository.findByPerson(personDetails.getPerson());
//        model.addAttribute("orders", orderList);
//        return "/user/orders";
//    }
//}



    // http:8080/localhost/admin/product/add
    // Метод по отображению страницы с возможностью добавления товаров
    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // Метод по добавлению продукта в БД через сервис->репозиторий
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {
        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }

        if (file_one != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_two != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_three != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_four != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_five != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/allOrder";
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/admin/allPerson";
    }

    // смена роли
    @GetMapping("/person/edit/{id}/{role}")
    public String setPersonRole(@PathVariable("id") int id, @PathVariable("role") String role) {
        personService.setPersonRole(id, role);
        return "redirect:/admin/allPerson";
    }
    // Метод по отображению страницы с возможностью редактирования товаров
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    // Метод по редактированию товара
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }


    //Поиск
    @PostMapping("/searc")
    public String productSearch(@RequestParam("searc") String searc, Model model) {
        if (!searc.isEmpty()) {
            model.addAttribute("searc_order", orderRepository.findByTitleGreaterThanEqual(searc));
        }
        model.addAttribute("value_searc", searc);
        model.addAttribute("order", orderService.getAllOrder());
        return "redirect:/admin/allOrder";

    }
    //Сохранение статуса
    @PostMapping("/order/changeStatus/{id}/{status}")
    public String changeStatus(@PathVariable("id") int id, @PathVariable("status") String status,Model model) {
        orderService.setStatus(id, status);
        List<String> statusList = Arrays.stream(Status.values()).map(Status::getName).collect(Collectors.toList());
        model.addAttribute("allStatus", statusList);
        return "order/allOrder";
    }

}