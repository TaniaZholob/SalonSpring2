package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.entity.Time_slots;
import com.tania.zholob.demo.model.repos.TimeSlotRepo;
import com.tania.zholob.demo.model.services.TimeSlotService;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotRepo timeSlotRepo;

    public TimeSlotServiceImpl(TimeSlotRepo timeSlotRepo) {
        this.timeSlotRepo = timeSlotRepo;
    }

    @Override
    public int updateTime(Time_slots time_slots) {
        return timeSlotRepo.updateDateTime(time_slots.getId(),time_slots.getDate_time());
    }
}
