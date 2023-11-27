package com.bus.repo;

import com.bus.models.RouteDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoRouteDate extends JpaRepository<RouteDate, Long> {
    List<RouteDate> findByRouteId(Long id);

}
