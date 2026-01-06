package com.school.Cais.Repositories;

import com.school.Cais.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsernameIgnoreCase(String username);
    boolean existsByUsername(String username);
}