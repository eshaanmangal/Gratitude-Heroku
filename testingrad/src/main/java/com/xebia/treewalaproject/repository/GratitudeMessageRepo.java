package com.xebia.treewalaproject.repository;

import com.xebia.treewalaproject.model.GratitudeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GratitudeMessageRepo extends JpaRepository<GratitudeMessage,Long> {
    List<GratitudeMessage> findByReceiverEmailId(String email);
}
