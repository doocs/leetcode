class Solution {
    public int numberOfRounds(String startTime, String finishTime) {
        int start = get(startTime), finish = get(finishTime);
        if (start > finish) {
            finish += 24 * 60;
        }
        start = (start + 14) / 15;
        finish /= 15;
        return Math.max(0, finish - start);
    }

    private int get(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
    }
}