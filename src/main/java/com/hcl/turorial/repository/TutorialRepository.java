package com.hcl.turorial.repository;


import com.hcl.turorial.dto.TutorialResponse;
import com.hcl.turorial.model.Tutorial;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Query("select t from Tutorial t where t.title=:title")
    Tutorial getTutorialByTitle(@Param("title")String title);
    boolean existsByTitle(String title);
    List<Tutorial> findAllByStatus(boolean b);
}
