class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int maxGapBefore = 0;
        int maxFreeTime = 0;
        int lastEnd = 0;        
        for (int i = 0; i < n; i++) {
            int meetingTime = endTime[i] - startTime[i];
            int nextStart = (i == n - 1) ? eventTime : startTime[i + 1];
            int freeTime = nextStart - lastEnd;
            if (meetingTime > maxGapBefore) {
                freeTime -= meetingTime;
            }
            
            maxFreeTime = Math.max(maxFreeTime, freeTime);
            maxGapBefore = Math.max(maxGapBefore, startTime[i] - lastEnd);
            lastEnd = endTime[i];
        }
        int maxGapAfter = 0;
        int lastStart = eventTime;
        for (int i = n - 1; i >= 0; i--) {
            int meetingTime = endTime[i] - startTime[i];
            int prevEnd = (i == 0) ? 0 : endTime[i - 1];
            int freeTime = lastStart - prevEnd;            
            if (meetingTime <= maxGapAfter) {
                maxFreeTime = Math.max(maxFreeTime, freeTime);
            }
            maxGapAfter = Math.max(maxGapAfter, lastStart - endTime[i]);
            lastStart = startTime[i];
        }
        return maxFreeTime;
    }
}