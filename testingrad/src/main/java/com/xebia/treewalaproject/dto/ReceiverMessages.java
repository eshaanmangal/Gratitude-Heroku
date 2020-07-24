package com.xebia.treewalaproject.dto;


import com.xebia.treewalaproject.model.GratitudeMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReceiverMessages {
    private Long emailCount;
    private List<GratitudeMessage> messagesReceiver;
}
