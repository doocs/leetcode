class Solution {
    public int minRotations(String s) {
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < s.length(); ++i) {
            int cur = s.charAt(i) - '0';
            int diff = Math.abs(cur - pre);
            ans += Math.min(diff, 10 - diff);
            pre = cur;
        }
        return ans;
    }
}