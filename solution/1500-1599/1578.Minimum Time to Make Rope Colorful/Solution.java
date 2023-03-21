class Solution {
    public int minCost(String colors, int[] neededTime) {
        int ans = 0;
        int n = neededTime.length;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            int s = 0, mx = 0;
            while (j < n && colors.charAt(j) == colors.charAt(i)) {
                s += neededTime[j];
                mx = Math.max(mx, neededTime[j]);
                ++j;
            }
            if (j - i > 1) {
                ans += s - mx;
            }
        }
        return ans;
    }
}