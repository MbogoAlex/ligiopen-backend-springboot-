package com.jabulani.ligiopen.model.user.entity;

import com.jabulani.ligiopen.model.club.entity.Player;
import com.jabulani.ligiopen.model.user.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private Boolean archived;
    private LocalDateTime archivedAt;

//    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL)
//    private Player player;
}
