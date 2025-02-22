package c3.coworktech.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import c3.coworktech.exceptions.Espacios.ByDisponibilidad;
import c3.coworktech.exceptions.Espacios.ByTipoException;
import c3.coworktech.exceptions.Espacios.DeleteById;
import c3.coworktech.exceptions.Espacios.GetEspaciosException;
import c3.coworktech.exceptions.Espacios.PatchEspacios;
import c3.coworktech.exceptions.Espacios.SaveEspacios;

@RestControllerAdvice
public class ExpaciosExceptionHandler {
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
    @ExceptionHandler(ByDisponibilidad.class)
    public ResponseEntity<Map<String, Object>> byDisponibilidad(ByDisponibilidad exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(SaveEspacios.class)
    public ResponseEntity<Map<String, Object>> saveEspacios(SaveEspacios exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PatchEspacios.class)
    public ResponseEntity<Map<String, Object>> patchEspacios(PatchEspacios exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DeleteById.class)
    public ResponseEntity<Map<String, Object>> deleteById(DeleteById exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}