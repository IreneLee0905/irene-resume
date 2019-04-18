package irene.web;


import lombok.Data;
import lombok.Getter;

@Data
public class ResponseDetail<T> {
  private Object data;
  private String message;

  ResponseDetail(T data, String message) {

    this.data = data;
    this.message = message;
  }
  ResponseDetail(){

  }

  public ResponseDetail(T data) {
    this.data = data;
  }
  public void message (String message){
    this.message = message;
  }


}
