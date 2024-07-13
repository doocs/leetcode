class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int ans = 0, last = 0;
        for (var e : meetings) {
            int st = e[0], ed = e[1];
            if (last < st) {
                ans += st - last - 1;
            }
            last = Math.max(last, ed);
        }
        ans += days - last;
        return ans;
    }
}