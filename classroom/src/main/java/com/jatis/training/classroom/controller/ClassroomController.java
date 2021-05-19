package com.jatis.training.classroom.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.classroom.dto.ClassroomDTO;
import com.jatis.training.classroom.entity.ClassroomEntity;
import com.jatis.training.classroom.repository.ClassroomRepository;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	private ClassroomRepository repo;
	
	@GetMapping("/{id}")
	@Cacheable(cacheNames = "classroom")
	public ClassroomDTO getClassroom(@PathVariable("id") UUID id) {
		ClassroomEntity entity = repo.findById(id).orElse(null);
		ClassroomDTO dto = new ClassroomDTO();
		BeanUtils.copyProperties(entity, dto);
		System.out.println("Getting classroom NOT FROM CACHE!!!!!");
		return dto;
	}
	
	@GetMapping("/list")
	@Cacheable(cacheNames = "classroom", key = "'list'")
	public List<ClassroomDTO> getClassrooms(){
		List<ClassroomDTO> dtos = new LinkedList<>();
		for (ClassroomEntity c : repo.findAll()) {
			ClassroomDTO dto = new ClassroomDTO();
			BeanUtils.copyProperties(c, dto);
			dtos.add(dto);
		}
		System.out.println("Get list NOT FROM CACHE!!!!!");
		return dtos;
	}
	
	@PostMapping
	@Caching(evict = 
		@CacheEvict(cacheNames = "classroom", key = "'list'"),
		put = @CachePut(cacheNames = "classroom", key = "#entity.id"))
	public ClassroomDTO saveClassroom(@RequestBody ClassroomEntity entity) {
		entity = repo.save(entity);
		ClassroomDTO dto = new ClassroomDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	@Caching(evict = 
			{@CacheEvict(cacheNames = "classroom", key = "'list'"),
			@CacheEvict(cacheNames = "classroom", key = "#id")})
	public ClassroomDTO deleteClassroom(UUID id) {
		ClassroomDTO dto = getClassroom(id);
		repo.deleteById(id);
		return dto;
	}
}
