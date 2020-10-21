package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Antique;

public interface TaskService {
    Runnable newRunnable(Antique antique);
}
