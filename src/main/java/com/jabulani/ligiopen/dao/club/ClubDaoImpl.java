package com.jabulani.ligiopen.dao.club;

import com.jabulani.ligiopen.model.club.entity.Club;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubDaoImpl implements ClubDao{
    private final EntityManager entityManager;
    @Autowired
    public ClubDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Club addClub(Club club) {
        entityManager.persist(club);
        return club;
    }

    @Override
    public Club updateClub(Club club) {
        entityManager.merge(club);
        return club;
    }

    @Override
    public Club getClubById(Integer id) {
        TypedQuery<Club> query = entityManager.createQuery("from Club where id = :id", Club.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Club> getClubs() {
        TypedQuery<Club> query = entityManager.createQuery("from Club", Club.class);
        return query.getResultList();
    }
}
