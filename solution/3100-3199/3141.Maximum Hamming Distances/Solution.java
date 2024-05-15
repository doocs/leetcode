class Solution {
    public int[] maxHammingDistances(int[] nums, int m) {
        int[] dist = new int[1 << m];
        Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        for (int x : nums) {
            dist[x] = 0;
            q.offer(x);
        }
        for (int k = 1; !q.isEmpty(); ++k) {
            for (int t = q.size(); t > 0; --t) {
                int x = q.poll();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        q.offer(y);
                        dist[y] = k;
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
        }
        return nums;
    }
}