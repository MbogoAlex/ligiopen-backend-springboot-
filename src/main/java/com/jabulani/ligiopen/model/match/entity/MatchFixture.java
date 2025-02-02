package com.jabulani.ligiopen.model.match.entity;

import com.jabulani.ligiopen.model.club.entity.Club;
import com.jabulani.ligiopen.model.match.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "match_fixture")
public class MatchFixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_location_id")
    private MatchLocation matchLocation;

    @OneToOne(mappedBy = "matchFixture", cascade = CascadeType.ALL)
    private PostMatchAnalysis postMatchAnalysis;

    @ManyToOne
    @JoinColumn(name = "home_club_id", nullable = false) // Foreign key for the home club
    private Club homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id", nullable = false) // Foreign key for the away club
    private Club awayClub;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "matchDateTime")
    private LocalDateTime matchDateTime;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @OneToMany(mappedBy = "matchFixture", cascade = CascadeType.ALL)
    private List<MatchStatistics> matchStatistics;

    @OneToMany(mappedBy = "matchFixture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchPlayer> matchPlayers;

}
