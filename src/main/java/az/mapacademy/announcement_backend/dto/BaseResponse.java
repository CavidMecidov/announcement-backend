package az.mapacademy.announcement_backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    T data;
    String message;
    Integer pageCount;

}
