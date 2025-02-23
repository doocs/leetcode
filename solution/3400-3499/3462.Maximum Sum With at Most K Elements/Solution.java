class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = grid.length;
        for (int i = 0; i < n; ++i) {
            int[] nums = grid[i];
            int limit = limits[i];
            Arrays.sort(nums);
            for (int j = 0; j < limit; ++j) {
                pq.offer(nums[nums.length - j - 1]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        long ans = 0;
        for (int x : pq) {
            ans += x;
        }
        return ans;
    }
}
