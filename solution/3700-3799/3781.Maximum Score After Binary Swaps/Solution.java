class Solution {
    public long maximumScore(int[] nums, String s) {
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            char c = s.charAt(i);
            pq.offer(x);
            if (c == '1') {
                ans += pq.poll();
            }
        }
        return ans;
    }
}
