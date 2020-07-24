package com.xebia.treewalaproject.controller;

import com.xebia.treewalaproject.dto.ReceiverMessages;
import com.xebia.treewalaproject.model.GratitudeMessage;
import com.xebia.treewalaproject.services.MessageService;
import com.xebia.treewalaproject.services.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/gratitudeMessage")
@CrossOrigin(origins = "*")
public class GratitudeMessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/postMessage")
    public ResponseEntity<GratitudeMessage> saveMessage(@RequestBody GratitudeMessage gratitudeMessage) {
        return ResponseEntity.ok().body(messageService.save(gratitudeMessage));
    }


    @GetMapping("/getMessages")
    public ResponseEntity<List<GratitudeMessage>> getAllMessageList(){
         return ResponseEntity.ok().body(messageService.findAll());
    }

    
    @GetMapping("/getMessagesByEmail")
    public ResponseEntity<ReceiverMessages> getMessageByEmail(@RequestParam(value="email",required = true)final String email){
        List <GratitudeMessage> tempList = messageService.findEmail(email);
        ReceiverMessages receiverMessages = new ReceiverMessages();
        receiverMessages.setMessagesReceiver(tempList);
        receiverMessages.setEmailCount((long) tempList.size());
        return  ResponseEntity.ok().body(receiverMessages);
    }

    
    @DeleteMapping("/deleteMessageByID")
    public  void deleteMessage (@RequestParam (value="id",required = true)final Long id){
        messageService.deleteMessage(id);
    }

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendmail(@RequestParam(value="emailId",required = true) final String email) throws Exception {
        Set<String> st = new HashSet<>();
        st.add(email);
        emailService.sendEmail(st);
        return ResponseEntity.ok().body("Email Sent");
    }
}
