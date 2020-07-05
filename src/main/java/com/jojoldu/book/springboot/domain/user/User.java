package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

  // @Gener
//  주키 생성 전략으로 JPA가 지원하는 것은 아래의 네 가지이다.
//  1. AUTO : (persistence provider가) 특정 DB에 맞게 자동 선택
//  2. IDENTITY : DB의 identity 컬럼을 이용
//  3. SEQUENCE : DB의 시퀀스 컬럼을 이용
//  4. TABLE : 유일성이 보장된 데이터베이스 테이블을 이용
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 설명 대로라면 nullable 은 DDL에 적용된다.
  //default값은 true이고 false로 설정하면 DDL 생성 시 not null 제약 조건이 붙는다.
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String picture;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(String name, String email, String picture, Role role) {
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.role = role;
  }

  public User update(String name, String picture) {
    this.name = name;
    this.picture = picture;

    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }

}
