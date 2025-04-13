package az.mapacademy.announcement_backend.dao;

import az.mapacademy.announcement_backend.entity.Category;

import java.util.List;

public interface CategoryDao  {
    List<Category> findAll();
}
