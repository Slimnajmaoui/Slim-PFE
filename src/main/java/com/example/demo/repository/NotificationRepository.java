package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
  List<Notification> findBysujetContaining(String sujet);
  List<Notification> findBysujet(String sujet);
}