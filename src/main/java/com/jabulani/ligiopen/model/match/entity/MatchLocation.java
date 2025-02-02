package com.jabulani.ligiopen.model.match.entity;

import com.jabulani.ligiopen.model.aws.File;
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
@Table(name = "match_location")
public class MatchLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private String county;

    private String town;

    @OneToMany(mappedBy = "matchLocation", cascade = CascadeType.ALL)
    private List<File> locationPhotos;

    @OneToMany(mappedBy = "matchLocation")
    private List<MatchFixture> matchFixtures;
}
