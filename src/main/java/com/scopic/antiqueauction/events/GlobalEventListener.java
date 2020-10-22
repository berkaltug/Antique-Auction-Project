package com.scopic.antiqueauction.events;

import com.scopic.antiqueauction.domain.converter.PastBidResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.PastBidService;
import com.scopic.antiqueauction.service.SaleService;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

import javax.validation.constraints.Past;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  GlobalEventListener{

    private final SimpMessagingTemplate messagingTemplate;
    private final PastBidService pastBidService;
    private final JavaMailSender mailSender;
    private final AntiqueService antiqueService;
    private final SaleService saleService;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String email;

    public GlobalEventListener(SimpMessagingTemplate messagingTemplate, PastBidService pastBidService, JavaMailSender mailSender, AntiqueService antiqueService, SaleService saleService, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.pastBidService = pastBidService;
        this.mailSender = mailSender;
        this.antiqueService = antiqueService;
        this.saleService = saleService;
        this.userService = userService;
    }

    @EventListener
    public void handleBidMadeEvent(BidMadeEvent bidMadeEvent){
        PastBid bid = bidMadeEvent.getBid();
        messagingTemplate.convertAndSend("/antique-topic/bid/"+bid.getAntique().getId(), PastBidResponseConverter.convert(bid));
        Set<User> users=findUsersWhoBid(bid.getAntique());
        sendBidMadeMail(bid,users);
    }
    @EventListener
    public void handleDeadlineEvent(DeadlineEvent deadlineEvent){
        Antique antique=deadlineEvent.getAntique();
        Set<User> users = findUsersWhoBid(antique);
        PastBid latestBid=pastBidService.getHighestPastBid(antique);
        saleService.insertSale(new Sale(latestBid.getAntique(), latestBid.getBid(), latestBid.getUser(), latestBid.getTime()));
        sendSaleNotificationMail(users,latestBid);
        sendBillMail(latestBid);
    }

    private void sendBidMadeMail(PastBid bid, Set<User> users){
        users.forEach(user->{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(this.email);
            message.setTo(user.getEmail());
            message.setSubject("Bid was made");
            message.setText("User " + bid.getUser().getUsername() + " made a bid of amount :"+bid.getBid().toString() +" $ to the " +bid.getAntique().getName());
            mailSender.send(message);
        });
    }

    private void sendBillMail(PastBid latestBid){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(this.email);
        message.setTo(latestBid.getUser().getEmail());
        message.setSubject("Congratulations");
        StringBuffer sb=new StringBuffer();
        sb.append("Congratiolations you bought the antique !\n");
        sb.append("Antique Name :" + latestBid.getAntique().getName() + "\n");
        sb.append("Your Bid : " + latestBid.getBid().toString() + "$\n");
        sb.append("Date :" + latestBid.getTime().toString() + "\n");
        message.setText(sb.toString());
        mailSender.send(message);
    }

    private void sendSaleNotificationMail(Set<User> users, PastBid latestBid){
        users.forEach(user->{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(this.email);
            message.setTo(user.getEmail());
            message.setSubject("An antique was sold");
            message.setText("The antique named " + latestBid.getAntique().getName() + " was sold to " + latestBid.getUser().getUsername()
                    + " for " + latestBid.getBid().toString() + " $");
            mailSender.send(message);
        });
    }

    private Set<User> findUsersWhoBid(Antique antique){
        List<PastBid> antiqueBids=pastBidService.getPastBidsByAntique(antique);
        return antiqueBids.stream().map(PastBid::getUser).collect(Collectors.toSet());
    }
}
