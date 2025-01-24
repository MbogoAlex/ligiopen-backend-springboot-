package com.jabulani.ligiopen.model.match.entity.events;

import com.jabulani.ligiopen.model.club.entity.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
//@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "foul_event")
public class FoulEvent extends MatchEvent{
    @ManyToOne
    @JoinColumn(name = "fouled_player_id", nullable = false)
    private Player fouledPlayer;

    private Boolean isYellowCard = false;
    private Boolean isRedCard = false;
}
