package mk.ukim.finki.emt_labs.web.rest;

import mk.ukim.finki.emt_labs.model.enumerations.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/categories")
public class CategoriesRestController {

    @GetMapping
    private ResponseEntity<Category[]> findAll() {
        return ResponseEntity.ok(Category.values());
    }
}
