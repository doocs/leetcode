class Solution {
    public int getMinimumTime(int[] time, int[][] fruits, int limit) {
        int ans = 0;
        for (int[] f : fruits) {
            int i = f[0], num = f[1];
            ans += (num + limit - 1) / limit * time[i];
        }
        return ans;
    }
}