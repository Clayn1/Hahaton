package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.entity.FirebaseToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirebaseTokenRepository extends JpaRepository<FirebaseToken, Integer> {
    @Transactional
    void deleteFirebaseTokenByToken(String token);
}
