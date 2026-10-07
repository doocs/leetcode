class Solution {
    public int minRotations(int n, String s) {
        int total = 0;
        for (int i = 1; i < n; ++i) {
            int diff = Math.abs(s.charAt(i) - s.charAt(i - 1));
            total += Math.min(diff, 10 - diff);
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);
        int toFirst = Math.min(first - '0', 10 - (first - '0'));
        int ans = total + Math.min(last - '0', 10 - (last - '0'));

        for (int i = 1; i < n; ++i) {
            char pre = s.charAt(i - 1);
            char cur = s.charAt(i);
            int diff = Math.abs(pre - cur);
            int edge = Math.min(diff, 10 - diff);
            diff = Math.abs(pre - last);
            int toLast = Math.min(diff, 10 - diff);
            ans = Math.min(ans, total - edge + toFirst + toLast);
        }

        return ans;
    }
}