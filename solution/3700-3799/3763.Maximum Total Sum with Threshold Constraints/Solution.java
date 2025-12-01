class Solution {
    public long maxSum(int[] nums, int[] threshold) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, Comparator.comparingInt(i -> threshold[i]));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        long ans = 0;
        for (int i = 0, step = 1;; ++step) {
            while (i < n && threshold[idx[i]] <= step) {
                tm.merge(nums[idx[i]], 1, Integer::sum);
                ++i;
            }
            if (tm.isEmpty()) {
                break;
            }
            int x = tm.lastKey();
            ans += x;
            if (tm.merge(x, -1, Integer::sum) == 0) {
                tm.remove(x);
            }
        }
        return ans;
    }
}
