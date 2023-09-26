package br.com.alr.course.configuration.exception;

public class DuplicatedItemException extends RuntimeException {

  public DuplicatedItemException(String message) {
    super(message);
  }
}
