package com.jabulani.ligiopen.dao.user;

import com.jabulani.ligiopen.model.user.entity.UserAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAccountDaoImpl implements UserAccountDao{
    private final EntityManager entityManager;
    @Autowired
    public UserAccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        entityManager.persist(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {
        entityManager.merge(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount getUserAccountById(Integer userId) {
        TypedQuery<UserAccount> query = entityManager.createQuery("from UserAccount where userId = :userId", UserAccount.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }

    @Override
    public UserAccount getUserAccountByEmail(String email) {
        TypedQuery<UserAccount> query = entityManager.createQuery("from UserAccount where email = :email", UserAccount.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }


    @Override
    public Boolean existsByEmail(String email) {
        TypedQuery<UserAccount> query = entityManager.createQuery("from UserAccount where email = :email", UserAccount.class);
        query.setParameter("email", email);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<UserAccount> getAllUsers() {
        TypedQuery<UserAccount> query = entityManager.createQuery("from UserAccount", UserAccount.class);
        return query.getResultList();
    }
}
