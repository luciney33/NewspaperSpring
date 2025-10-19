package newspaperoot.ui.error;

import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.Error.DatabaseError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppError.class)
    public AppError appErrorHandler(AppError appError){
        return appError;
    }
    @ExceptionHandler(DatabaseError.class)
    public DatabaseError databaseErrorHandler(DatabaseError databaseError){
        return databaseError;
    }
}
