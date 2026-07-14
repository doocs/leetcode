class Solution {
    public int secondsBetweenTimes(String startTime, String endTime) {
        return f(endTime) - f(startTime);
    }

    private int f(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 3600 + Integer.parseInt(s.substring(3, 5)) * 60
            + Integer.parseInt(s.substring(6));
    }
}