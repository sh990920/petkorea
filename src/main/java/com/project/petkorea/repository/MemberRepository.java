package com.project.petkorea.repository;

import com.project.petkorea.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Object> {
    Member findById(String id);
}
