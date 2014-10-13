


package org.homestead.robotics.util;

public class Timer
{
    private double duration;
    
    private double currentTime;
    private boolean set;
    
    public static class TimerState
    {
        public static final TimerState RUNNING = new TimerState(1);
        public static final TimerState UNSET = new TimerState(0);
        public static final TimerState DONE = new TimerState(-1);
        
        private int val;
        
        private TimerState(int val)
        {
            this.val = val;
        }
        
        public boolean equals(TimerState other)
        {
            return this.val == other.val;
        }
    }
    
    public Timer(double duration)
    {
        this.duration = duration;
        
        this.currentTime = 0;
        this.set = false;
    }
    
    public void set()
    {
        this.set = true;
        this.currentTime = 0;
    }
    
    public TimerState step()
    {
        if(this.set)
        {
            this.currentTime++;
            if(this.currentTime >= this.duration) return TimerState.DONE;
            else return TimerState.RUNNING;
        }
        else return TimerState.UNSET;
    }
    
    public void terminate()
    {
        set = false;
        currentTime = 0;
    }
    
    public TimerState getState()
    {
        if(this.set)
        {
            if(this.currentTime >= this.duration) return TimerState.DONE;
            else return TimerState.RUNNING;
        }
        else return TimerState.UNSET;
    }
}