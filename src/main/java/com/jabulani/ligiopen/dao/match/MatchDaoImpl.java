package com.jabulani.ligiopen.dao.match;

import com.jabulani.ligiopen.model.match.entity.MatchCommentary;
import com.jabulani.ligiopen.model.match.entity.MatchFixture;
import com.jabulani.ligiopen.model.match.entity.MatchLocation;
import com.jabulani.ligiopen.model.match.entity.PostMatchAnalysis;
import com.jabulani.ligiopen.model.match.entity.events.MatchEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchDaoImpl implements MatchDao{
    private final EntityManager entityManager;
    @Autowired
    public MatchDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MatchLocation createMatchLocation(MatchLocation matchLocation) {
        entityManager.persist(matchLocation);
        return matchLocation;
    }

    @Override
    public MatchLocation updateMatchLocation(MatchLocation matchLocation) {
        entityManager.merge(matchLocation);
        return matchLocation;
    }

    @Override
    public MatchLocation getMatchLocationById(Integer id) {
        TypedQuery<MatchLocation> query = entityManager.createQuery("from MatchLocation where id = :id", MatchLocation.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<MatchLocation> getMatchLocations() {
        TypedQuery<MatchLocation> query = entityManager.createQuery("from MatchLocation", MatchLocation.class);
        return query.getResultList();
    }

    @Override
    public MatchFixture createMatchFixture(MatchFixture matchFixture) {
        entityManager.persist(matchFixture);
        return matchFixture;
    }

    @Override
    public MatchFixture updateMatchFixture(MatchFixture matchFixture) {
        entityManager.merge(matchFixture);
        return matchFixture;
    }

    @Override
    public MatchFixture getMatchFixtureById(Integer id) {
        TypedQuery<MatchFixture> query = entityManager.createQuery("from MatchFixture where id = :id", MatchFixture.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<MatchFixture> getMatchFixtures() {
        TypedQuery<MatchFixture> query = entityManager.createQuery("from MatchFixture", MatchFixture.class);
        return query.getResultList();
    }

    @Override
    public MatchEvent createMatchEvent(MatchEvent matchEvent) {
        entityManager.persist(matchEvent);
        return matchEvent;
    }

    @Override
    public MatchEvent updateMatchEvent(MatchEvent matchEvent) {
        entityManager.merge(matchEvent);
        return matchEvent;
    }

    @Override
    public MatchEvent getMatchEventById(Integer id) {
        TypedQuery<MatchEvent> query = entityManager.createQuery("from MatchEvent where id = :id", MatchEvent.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<MatchEvent> getMatchEvents() {
        TypedQuery<MatchEvent> query = entityManager.createQuery("from MatchEvent", MatchEvent.class);
        return query.getResultList();
    }

    @Override
    public PostMatchAnalysis createPostMatchAnalysis(PostMatchAnalysis postMatchAnalysis) {
        entityManager.persist(postMatchAnalysis);
        return postMatchAnalysis;
    }

    @Override
    public PostMatchAnalysis updatePostMatchAnalysis(PostMatchAnalysis postMatchAnalysis) {
        return entityManager.merge(postMatchAnalysis);
    }

    @Override
    public PostMatchAnalysis getPostMatchAnalysisById(Integer id) {
        TypedQuery<PostMatchAnalysis> query = entityManager.createQuery("from PostMatchAnalysis where id = :id", PostMatchAnalysis.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<PostMatchAnalysis> getAllPostMatchAnalysis() {
        TypedQuery<PostMatchAnalysis> query = entityManager.createQuery("from PostMatchAnalysis", PostMatchAnalysis.class);
        return query.getResultList();
    }

    @Override
    public MatchCommentary createMatchCommentary(MatchCommentary matchCommentary) {
        entityManager.persist(matchCommentary);
        return matchCommentary;
    }

    @Override
    public MatchCommentary updateMatchCommentary(MatchCommentary matchCommentary) {
        entityManager.merge(matchCommentary);
        return matchCommentary;
    }

    @Override
    public MatchCommentary getMatchCommentaryById(Integer id) {
        TypedQuery<MatchCommentary> query = entityManager.createQuery("from MatchCommentary where id = :id", MatchCommentary.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<MatchCommentary> getAllMatchCommentaries() {
        TypedQuery<MatchCommentary> query = entityManager.createQuery("from MatchCommentary", MatchCommentary.class);
        return query.getResultList();
    }
}
