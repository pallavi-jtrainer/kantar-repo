package com.edforce.minibankapplication.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edforce.minibankapplication.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumber(String accountNumber);
	List<Account> findAllByCustomerId(Long customerId);

	@Modifying
	@Transactional
	@Query("update Account a set a.balance = :balance where a.accountNumber = :accountNumber")
	int updateBalanceByAccountNumber(@Param("accountNumber") String accountNumber, @Param("balance") BigDecimal balance);
}
