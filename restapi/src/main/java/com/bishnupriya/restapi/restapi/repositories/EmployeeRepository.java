package com.bishnupriya.restapi.restapi.repositories;

import com.bishnupriya.restapi.restapi.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
  @Query("SELECT e FROM EmployeeEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<EmployeeEntity> findByName_(@Param("name") String name);

}
