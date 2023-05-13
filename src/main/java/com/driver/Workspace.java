package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxMeetings = 0;
        for (Meeting meeting1 : this.calendar) {
            int count = 0;
            for (Meeting meeting2 : this.calendar) {
                if (meeting1 != meeting2) {
                    if ((meeting1.getStartTime().isBefore(meeting2.getStartTime())
                            && meeting1.getEndTime().isAfter(meeting2.getStartTime()))
                            || (meeting1.getStartTime().isBefore(meeting2.getEndTime())
                            && meeting1.getEndTime().isAfter(meeting2.getEndTime()))
                            || (meeting1.getStartTime().equals(meeting2.getStartTime())
                            && meeting1.getEndTime().equals(meeting2.getEndTime()))) {
                        count++;
                    }
                }
            }
            if (count > maxMeetings) {
                maxMeetings = count;
            }
        }
        return maxMeetings;
    }
}
