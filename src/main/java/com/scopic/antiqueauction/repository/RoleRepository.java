package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
