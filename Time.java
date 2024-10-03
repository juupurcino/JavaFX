public class Time {

    static final long secondsTicks = 1000;
    static final long minuteTicks = secondsTicks * 60;
    static final long hourTicks = minuteTicks * 60;
    static final long dayTicks = hourTicks* 24;
    static final long yearTicks = dayTicks * 365;
    
    private long ticks = 0;

    public Time(int year, int dayOfYear, int hour, int minute, int second){

        this.ticks = 
            yearTicks * year + 
            dayTicks * dayOfYear + 
            hourTicks * hour + 
            minuteTicks * minute + 
            secondsTicks * second; 
    }

    public int getYear(){
        return (int)(ticks/yearTicks) + 1970;
    }

    public int getDay(){
        long rest = ticks % yearTicks;
        return (int)(rest/1000);
    }
    
    public int getHour(){
        long rest = ticks % dayTicks;
        return (int)(rest/1000);
    }
    
    public int getMinute(){
        long rest = ticks % hourTicks;
        return (int)(rest/1000);
    }

    public int getTotalSeconds(){
        long rest = ticks % hourTicks;
        return (int)(ticks/1000);
    }

    
}
