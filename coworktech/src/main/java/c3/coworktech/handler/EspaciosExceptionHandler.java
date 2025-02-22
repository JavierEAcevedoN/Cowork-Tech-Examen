package c3.coworktech.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.coworktech.exceptions.Espacios.ByDisponibilidadException;
import c3.coworktech.exceptions.Espacios.ByTipoException;
import c3.coworktech.exceptions.Espacios.DeleteByIdException;
import c3.coworktech.exceptions.Espacios.GetEspaciosException;
import c3.coworktech.exceptions.Espacios.PatchEspaciosException;
import c3.coworktech.exceptions.Espacios.SaveEspaciosException;

@RestControllerAdvice
public class EspaciosExceptionHandler {
    @ExceptionHandler(GetEspaciosException.class)
    public ResponseEntity<Map<String, Object>> espaciosVoid(GetEspaciosException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ByTipoException.class)
    public ResponseEntity<Map<String, Object>> byTipo(ByTipoException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ByDisponibilidadException.class)
    public ResponseEntity<Map<String, Object>> byDisponibilidad(ByDisponibilidadException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(SaveEspaciosException.class)
    public ResponseEntity<Map<String, Object>> saveEspacios(SaveEspaciosException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PatchEspaciosException.class)
    public ResponseEntity<Map<String, Object>> patchEspacios(PatchEspaciosException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DeleteByIdException.class)
    public ResponseEntity<Map<String, Object>> deleteById(DeleteByIdException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}