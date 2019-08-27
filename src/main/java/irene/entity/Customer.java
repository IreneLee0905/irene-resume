package irene.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import irene.common.Gender;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "FIRST_NAME", length = 50)
  private String firstName;

  @Column(name = "LAST_NAME", length = 20)
  private String lastName;

  @Column(name = "ACCOUNT", length = 50)
  private String account;

  @Column(name = "PASSWORD", length = 20)
  private String password;

  @Column(name = "BIRTHDAY")
  private LocalDate birthday;

  @Column(name = "ADDRESS", length = 50)
  private String address;

  @Column(name = "CITY", length = 20)
  private String city;

  @Column(name = "STATE", length = 20)
  private String state;

  @Column(name = "ZIP_CODE")
  private Integer zipCode;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "GENDER")
  private Gender gender;

  @Column(name = "LOGIN")
  private Boolean login;
}
