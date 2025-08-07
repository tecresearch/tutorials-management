package com.hcl.turorial.repository;


import com.hcl.turorial.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Query("select t from Tutorial t where t.title=:tittle")
    Tutorial getTutorialByTitle(@Param("tittle")String title);

    boolean existsByTitle(String title);
}
