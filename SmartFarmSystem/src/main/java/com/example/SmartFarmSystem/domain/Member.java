package com.example.SmartFarmSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "members")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    public String getmid() {
        return memberId;
    }

    public void setmid(String member_id) {
        this.memberId = member_id;
    }

    public String getmpw() {
        return memberPw;
    }

    public void setmpw(String member_pw) {
        this.memberPw = member_pw;
    }

    @JsonProperty("memberId")
    @Column(nullable = true)
    private String memberId;

    @JsonProperty("memberPw")
    @Column(nullable = true)
    private String memberPw;
}