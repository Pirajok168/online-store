package ru.eremin.onlinestore.controller;

import org.springframework.web.bind.annotation.*;
import ru.eremin.onlinestore.model.Item;
import ru.eremin.onlinestore.repo.ItemRepository;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item newItem) {
        return repository.findById(id).map(item -> {
            item.setName(newItem.getName());
            item.setDescription(newItem.getDescription());
            item.setPrice(newItem.getPrice());
            item.setCategory(newItem.getCategory());
            item.setRating(newItem.getRating());
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam(required = false) String category,
                                  @RequestParam(required = false) Integer rating) {
        if (category != null) {
            return repository.findByCategory(category);
        } else if (rating != null) {
            return repository.findByRating(rating);
        }
        return repository.findAll();
    }
}
