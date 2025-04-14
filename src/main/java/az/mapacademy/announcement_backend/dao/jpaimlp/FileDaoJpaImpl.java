package az.mapacademy.announcement_backend.dao.jpaimlp;


import az.mapacademy.announcement_backend.dao.FileDao;
import az.mapacademy.announcement_backend.entity.File;
import az.mapacademy.announcement_backend.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FileDaoJpaImpl implements FileDao {
    private final FileRepository fileRepository;

    @Override
    public File add(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void remove(Long id) {
        fileRepository.deleteById(id);
    }
}