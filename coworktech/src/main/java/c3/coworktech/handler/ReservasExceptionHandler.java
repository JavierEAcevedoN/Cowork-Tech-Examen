package c3.coworktech.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import c3.coworktech.exceptions.Reservas.ByEstadoException;
import c3.coworktech.exceptions.Reservas.ByFechaException;
import c3.coworktech.exceptions.Reservas.GetReservasException;
import c3.coworktech.exceptions.Reservas.PatchReservaException;
import c3.coworktech.exceptions.Reservas.SaveReservasException;

@ControllerAdvice
public class ReservasExceptionHandler {
    @ExceptionHandler(GetReservasException.class)
    public ResponseEntity<Map<String, Object>> reservasVoid(GetReservasException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ByEstadoException.class)
    public ResponseEntity<Map<String, Object>> byEstado(ByEstadoException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ByFechaException.class)
    public ResponseEntity<Map<String, Object>> byFecha(ByFechaException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(SaveReservasException.class)
    public ResponseEntity<Map<String, Object>> saveReserva(SaveReservasException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PatchReservaException.class)
    public ResponseEntity<Map<String, Object>> patchReserva(PatchReservaException exc) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", exc.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}