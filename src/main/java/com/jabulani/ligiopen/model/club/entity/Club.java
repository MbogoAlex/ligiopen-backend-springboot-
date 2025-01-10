package com.jabulani.ligiopen.model.club.entity;

import com.jabulani.ligiopen.model.aws.File;
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
}
