package com.example.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.role;


public interface RoleRopository extends MongoRepository<role, String> {

	role findByProfil(String profil);

}
