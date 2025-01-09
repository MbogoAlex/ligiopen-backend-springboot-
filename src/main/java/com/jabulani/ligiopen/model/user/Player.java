package com.jabulani.ligiopen.model.user;

import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.club.Club;
import com.jabulani.ligiopen.model.club.PlayerClub;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private PlayerPosition playerPosition;

    private Integer age;

    private Double height;

    private Double weight;

    private String country;

    private String county;

    private String town;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerClub> playerClubs;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<File> files;
}
