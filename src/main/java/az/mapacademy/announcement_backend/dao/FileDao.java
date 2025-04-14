package az.mapacademy.announcement_backend.dao;


import az.mapacademy.announcement_backend.entity.File;


public interface FileDao {
    File add(File file);

    void remove(Long id);
}