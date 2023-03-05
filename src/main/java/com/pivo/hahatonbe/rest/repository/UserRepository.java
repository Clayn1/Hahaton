package com.pivo.hahatonbe.rest.repository;

import com.pivo.hahatonbe.model.dto.UserCredentials;
import com.pivo.hahatonbe.model.dto.UserData;
import com.pivo.hahatonbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new com.pivo.hahatonbe.model.dto.UserCredentials(u.id, u.email, u.password, u.role) from users as u where u.email = :email")
    UserCredentials findUserCredentialsByEmail(@Param("email") String email);
    @Query("select new com.pivo.hahatonbe.model.dto.UserData(u.id, u.email, u.name, u.role) from users as u where u.email = :email")
    UserData findUserByEmail(@Param("email") String email);
}
