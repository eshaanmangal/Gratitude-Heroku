package com.xebia.treewalaproject.services.impl;

import com.xebia.treewalaproject.model.GratitudeMessage;
import com.xebia.treewalaproject.repository.GratitudeCountRepo;
import com.xebia.treewalaproject.repository.GratitudeMessageRepo;
import com.xebia.treewalaproject.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private GratitudeMessageRepo gratitudeMessageRepo;

    @Autowired
    private GratitudeCountRepo gratitudeCountRepo;

    @Override
    public GratitudeMessage save(GratitudeMessage gratitudeMessage) {
        return gratitudeMessageRepo.save(gratitudeMessage);
    }

    @Override
    public List<GratitudeMessage> findAll() {
        return gratitudeMessageRepo.findAll();
    }

    @Override
    public void deleteMessage(Long id) {
        gratitudeMessageRepo.deleteById(id);
    }

    @Override
    public List<GratitudeMessage> findEmail(String email) {
        return gratitudeMessageRepo.findByReceiverEmailId(email);
    }

}
