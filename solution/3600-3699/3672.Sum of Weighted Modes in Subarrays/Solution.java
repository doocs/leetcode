class Solution {
    public long modeWeight(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
        }

        long ans = 0;

        Supplier<Long> getMode = () -> {
            while (true) {
                int[] top = pq.peek();
                int val = top[1];
                int freq = -top[0];
                if (cnt.getOrDefault(val, 0) == freq) {
                    return 1L * freq * val;
                }
                pq.poll();
            }
        };

        ans += getMode.get();

        for (int i = k; i < nums.length; i++) {
            int x = nums[i], y = nums[i - k];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
            cnt.merge(y, -1, Integer::sum);
            pq.offer(new int[] {-cnt.get(y), y});
            ans += getMode.get();
        }

        return ans;
    }
}
