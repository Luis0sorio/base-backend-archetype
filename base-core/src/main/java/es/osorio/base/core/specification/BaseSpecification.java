package es.osorio.base.core.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification {

  protected static <T> Specification<T> equalsIfPresent(String field, Object value) {
    return (root, query, cb) ->
      value == null
        ? null
        : cb.equal(root.get(field), value);
  }

  protected static <T> Specification<T> likeIgnoreCaseIfPresent(String field, String value) {
    return (root, query, cb) ->
      value == null || value.isBlank()
        ? null
        : cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
  }
}
