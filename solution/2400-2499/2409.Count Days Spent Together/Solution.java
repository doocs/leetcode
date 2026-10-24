class Solution {
    static final int[] f = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public int countDaysTogether(
        String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        int d1
            = f[Integer.parseInt(start.substring(0, 2)) - 1] + Integer.parseInt(start.substring(3));
        int d2 = f[Integer.parseInt(end.substring(0, 2)) - 1] + Integer.parseInt(end.substring(3));
        return Math.max(d2 - d1 + 1, 0);
    }
}