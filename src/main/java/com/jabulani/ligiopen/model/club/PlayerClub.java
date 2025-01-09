package com.jabulani.ligiopen.model.club;

import com.jabulani.ligiopen.model.user.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "player_club")
public class PlayerClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    private LocalDateTime createdAt;

    private Boolean archived;

    private LocalDateTime archivedAt;
}
