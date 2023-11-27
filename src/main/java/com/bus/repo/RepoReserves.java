package com.bus.repo;

import com.bus.models.Reserves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoReserves extends JpaRepository<Reserves, Long> {
    List<Reserves> findByPassport(String passport);
    List<Reserves> findByDateId(Long id);
}
