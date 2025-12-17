package es.osorio.base.rest.controller.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

public abstract class BaseController {

  protected <T>ResponseEntity<T> ok (T body) {
    return ResponseEntity.ok(body);
  }

  protected ResponseEntity<Void> created() {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  protected <T> ResponseEntity<T> created(T body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }

  protected ResponseEntity<Void> noContent() {
    return ResponseEntity.noContent().build();
  }

  protected <T> ResponseEntity<Page<T>> ok(Page<T> page) {
    return ResponseEntity.ok(page);
  }
}
