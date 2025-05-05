package ru.eremin.onlinestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eremin.onlinestore.model.Category;
import ru.eremin.onlinestore.model.Item;

import java.util.List;



public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory_Id(Long categoryId);
    List<Item> findByRating(int rating);
}