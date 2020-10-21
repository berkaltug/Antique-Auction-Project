package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.events.EventPublisher;
import com.scopic.antiqueauction.service.PastBidService;
import com.scopic.antiqueauction.service.SaleService;
import com.scopic.antiqueauction.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

   private EventPublisher eventPublisher;
    @Autowired
    public TaskServiceImpl(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Runnable newRunnable(Antique antique) {
        return new Runnable() {
            @Override
            public void run() {
                eventPublisher.publishDeadlineEvent(antique);
            }
        };
    }
}
