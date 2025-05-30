package com.jabulani.ligiopen.model.user.entity;

import com.jabulani.ligiopen.model.club.entity.Club;
import com.jabulani.ligiopen.model.user.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // Relationship for TEAM_ADMIN (one admin per club, but a user can admin multiple clubs)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administered_club_id")
    private Club administeredClub;

    // Relationship for MANAGER (one manager can manage one club)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managed_club_id")
    private Club managedClub;

    // Relationship for COACH (one coach can coach one club)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coached_club_id")
    private Club coachedClub;

    @ManyToMany
    @JoinTable(
            name = "user_bookmarked_clubs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private List<Club> favoriteClubs = new ArrayList<>();

    // Validation to ensure only one club relationship is set based on role
    @PrePersist
    @PreUpdate
    private void validateClubRelationships() {
        if (role == null) {
            return;
        }

        switch (role) {
            case TEAM_ADMIN:
                if (managedClub != null || coachedClub != null) {
                    throw new IllegalStateException("TEAM_ADMIN can only have administeredClub");
                }
                break;
            case MANAGER:
                if (administeredClub != null || coachedClub != null) {
                    throw new IllegalStateException("MANAGER can only have managedClub");
                }
                break;
            case COACH:
                if (administeredClub != null || managedClub != null) {
                    throw new IllegalStateException("COACH can only have coachedClub");
                }
                break;
            default:
                if (administeredClub != null || managedClub != null || coachedClub != null) {
                    throw new IllegalStateException("Only TEAM_ADMIN, MANAGER, or COACH can have club relationships");
                }
        }
    }
}