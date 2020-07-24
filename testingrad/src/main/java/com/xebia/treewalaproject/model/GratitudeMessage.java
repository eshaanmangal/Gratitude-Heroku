package com.xebia.treewalaproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class GratitudeMessage implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Gratitude_Message")
    private String gratitudeMessage;

    @CreationTimestamp
    @Column(name = "Message_Creationion_Time")
    private LocalDateTime createdDate;

    @Column(name = "Gratitude_Sender")
    private String sender;

    @Column(name = "Gratitude_Receiver")
    private String receiver;

    @Column(name = "Sender_Email_ID")
    private String senderEmailId;

    @Column(name = "Receiver_Email_ID")
    private String receiverEmailId;

}
