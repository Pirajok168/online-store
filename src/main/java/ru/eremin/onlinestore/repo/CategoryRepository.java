package ru.eremin.onlinestore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eremin.onlinestore.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}