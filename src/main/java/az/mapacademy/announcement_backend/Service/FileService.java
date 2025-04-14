package az.mapacademy.announcement_backend.Service;


import az.mapacademy.announcement_backend.dao.FileDao;
import az.mapacademy.announcement_backend.dto.FileResponse;
import az.mapacademy.announcement_backend.entity.File;
import az.mapacademy.announcement_backend.Mapper.FileMapper;
import az.mapacademy.announcement_backend.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class FileService {
    private final FileDao fileDao;
    private final FileMapper fileMapper;

    public File add(MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
            var fileName = multipartFile.getOriginalFilename();//picture.jpg
            var fileType = multipartFile.getContentType();//image/jpeg

            //save file physically
            FileUtil.saveFile(fileName, multipartFile);

            //save file information to database
            File file = new File();
            file.setName(fileName);
            file.setType(fileType);
            file.setCreatedAt(LocalDateTime.now());
            file = fileDao.add(file);

            return file;
        }

        return null;
    }

    public FileResponse downloadFile(File file) {
        byte[] arr = FileUtil.readFile(file.getName());
        return fileMapper.toFileResponse(file, arr);
    }

    public void deleteFile(File file) {
        FileUtil.deleteFile(file.getName());
        fileDao.remove(file.getFileId());
    }
}