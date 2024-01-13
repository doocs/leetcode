class Solution {
    public int numberOfRounds(String loginTime, String logoutTime) {
        int a = f(loginTime), b = f(logoutTime);
        if (a > b) {
            b += 1440;
        }
        return Math.max(0, b / 15 - (a + 14) / 15);
    }

    private int f(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        return h * 60 + m;
    }
}