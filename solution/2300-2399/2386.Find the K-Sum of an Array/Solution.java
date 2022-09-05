class Solution {
    public long kSum(int[] nums, int k) {
        long mx = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                mx += nums[i];
            } else {
                nums[i] *= -1;
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq
            = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.offer(new Pair<>(0L, 0));
        while (--k > 0) {
            var p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < n) {
                pq.offer(new Pair<>(s + nums[i], i + 1));
                if (i > 0) {
                    pq.offer(new Pair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return mx - pq.peek().getKey();
    }
}