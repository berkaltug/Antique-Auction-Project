package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.converter.PastBidResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.service.PastBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  GlobalEventListener{

    private final SimpMessagingTemplate messagingTemplate;
    private final PastBidService pastBidService;
    private final JavaMailSender mailSender;


    public GlobalEventListener(SimpMessagingTemplate messagingTemplate, PastBidService pastBidService, JavaMailSender mailSender) {
        this.messagingTemplate = messagingTemplate;
        this.pastBidService = pastBidService;
        this.mailSender = mailSender;
    }

    @EventListener
    public void handleBidMadeEvent(BidMadeEvent bidMadeEvent){
        PastBid bid = bidMadeEvent.getBid();
        messagingTemplate.convertAndSend("/antique-topic/bid/"+bid.getAntique().getId(), PastBidResponseConverter.convert(bid));
        List<PastBid> antiqueBids=pastBidService.getPastBidsByAntique(bid.getAntique());
        Set<User> users=antiqueBids.stream().map(PastBid::getUser).collect(Collectors.toSet());
        sendNotificationMails(bid,users);
    }
    @EventListener
    public void handleDeadlineEvent(DeadlineEvent deadlineEvent){
        Antique antique=deadlineEvent.getAntique();
        System.out.println(antique.getId() +" "+ antique.getName() + " antique deadline finished");
    }

    private void sendNotificationMails(PastBid bid,Set<User> users){
        users.forEach(user->{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("berkaltug96@gmail.com");
            message.setTo(user.getEmail());
            message.setSubject("Bid has been made");
            message.setText("User " + bid.getUser().getUsername() + " made a bid of amount :"+bid.getBid().toString() +" $ to the " +bid.getAntique().getName());
            mailSender.send(message);
        });
    }
}
