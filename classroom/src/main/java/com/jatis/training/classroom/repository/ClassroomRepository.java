package com.jatis.training.classroom.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.training.classroom.entity.ClassroomEntity;

public interface ClassroomRepository extends PagingAndSortingRepository<ClassroomEntity, UUID> {
}
