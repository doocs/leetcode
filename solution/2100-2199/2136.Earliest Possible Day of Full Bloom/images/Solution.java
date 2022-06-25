class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[]{plantTime[i], growTime[i]};
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int ans = 0;
        int t = 0;
        for (int[] e : arr) {
            t += e[0];
            ans = Math.max(ans, t + e[1]);
        }
        return ans;
    }
}