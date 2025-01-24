package com.jabulani.ligiopen.model.club.entity;

import com.jabulani.ligiopen.model.aws.File;
import com.jabulani.ligiopen.model.match.entity.MatchFixture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String description;

    private String country;

    private String county;

    private String town;

    private LocalDate startedOn;

    private LocalDateTime createdAt;

    private Boolean archived;

    private LocalDateTime archivedAt;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<PlayerClub> playerClubs;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<File> files;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_logo_id")
    private File clubLogo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_main_photo_id")
    private File clubMainPhoto;

    @OneToMany(mappedBy = "homeClub", cascade = CascadeType.ALL)
    private List<MatchFixture> homeFixtures;

    @OneToMany(mappedBy = "awayClub", cascade = CascadeType.ALL)
    private List<MatchFixture> awayFixtures;
}
