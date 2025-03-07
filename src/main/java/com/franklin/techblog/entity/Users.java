package com.franklin.techblog.entity;
import jakarta.persistence.*;
import lombok.*;




    @Entity
    @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
    public class Users  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="user_id")
        private Long userId;

        private String name;

        private String email;

        @Column(name="mobile_number")
        private String mobileNumber;

        private String password;

        private String username;


    }


