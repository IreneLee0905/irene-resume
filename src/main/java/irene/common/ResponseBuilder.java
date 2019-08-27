package irene.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public class ResponseBuilder<T> {

  private HttpStatus status;
  private String message;
  private T data;
  private HttpHeaders header;

  public ResponseBuilder() {}

  public ResponseBuilder(HttpStatus status, T data) {
    this.status = status;
    this.data = data;
  }

  public ResponseEntity badRequest(String message) {
    this.status = HttpStatus.BAD_REQUEST;
    this.message = message;
    return build();
  }
  public ResponseEntity badRequest() {
    this.status = HttpStatus.BAD_REQUEST;
    this.message = "some error occurs on the server";
    return build();
  }

  public ResponseEntity ok() {
    this.status = HttpStatus.OK;
    return build();
  }

  public ResponseEntity ok(T data) {
    this.status = HttpStatus.OK;
    this.data = data;
    return build();
  }

  public ResponseBuilder message(String message) {
    this.message = message;
    return this;
  }

  public ResponseBuilder data(T data) {
    this.data = data;
    return this;
  }

  public ResponseBuilder status(HttpStatus status) {
    this.status = status;
    return this;
  }

  // TODO add header function
  public ResponseBuilder header(HttpHeaders header) {

    this.header = header;
    return this;
  }

  public ResponseEntity<ResponseDetail> build() {

    Assert.notNull(status, "HttpStatus can not be null!");
    ResponseDetail responseDetail;

    responseDetail = new ResponseDetail<>(data, message);

    return new ResponseEntity<ResponseDetail>(responseDetail, status);
  }
}
