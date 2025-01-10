package com.jabulani.ligiopen.model.aws;

import com.jabulani.ligiopen.model.club.Club;
import com.jabulani.ligiopen.model.club.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToOne(mappedBy = "clubLogo", cascade = CascadeType.ALL)
    private Club clubAsLogo;
}
