class Solution {
    public int translateNum(int num) {
        return cal(String.valueOf(num));
    }

    private int cal(String s) {
        int n = s.length();
        if (n < 2) {
            return 1;
        }
        int t = Integer.parseInt(s.substring(0, 2));
        return t < 10 || t > 25 ? cal(s.substring(1)) : cal(s.substring(1)) + cal(s.substring(2));
    }
}