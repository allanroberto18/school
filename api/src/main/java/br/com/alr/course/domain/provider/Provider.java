package br.com.alr.course.domain.provider;

import br.com.alr.course.configuration.exception.NotFoundException;

public interface Provider<T> {
  T findById(Integer id) throws NotFoundException;
}
