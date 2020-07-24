package com.xebia.treewalaproject.services;

import com.xebia.treewalaproject.model.GratitudeMessage;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    GratitudeMessage save(GratitudeMessage gratitudeMessage);
    List<GratitudeMessage> findAll();
    void deleteMessage(Long id);
    List<GratitudeMessage> findEmail(String email);
}