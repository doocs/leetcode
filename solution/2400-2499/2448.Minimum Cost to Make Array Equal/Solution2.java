class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums[i], cost[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        long mid = sum(cost) / 2;
        long s = 0, ans = 0;
        for (var e : arr) {
            int x = e[0], c = e[1];
            s += c;
            if (s > mid) {
                for (var t : arr) {
                    ans += (long) Math.abs(t[0] - x) * t[1];
                }
                break;
            }
        }
        return ans;
    }

    private long sum(int[] arr) {
        long s = 0;
        for (int v : arr) {
            s += v;
        }
        return s;
    }
}