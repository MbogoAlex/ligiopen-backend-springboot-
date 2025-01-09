package com.jabulani.ligiopen.model.club;

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
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<PlayerClub> playerClubs;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<File> files;
}
