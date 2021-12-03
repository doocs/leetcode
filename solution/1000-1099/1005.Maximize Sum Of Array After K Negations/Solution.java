class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            ans += num;
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        for (int i = -100; i < 0; ++i) {
            if (counter.getOrDefault(i, 0) > 0) {
                int ops = Math.min(counter.get(i), k);
                ans -= (i * ops * 2);
                counter.put(i, counter.getOrDefault(i, 0) - ops);
                counter.put(-i, counter.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && (k % 2) == 1 && counter.get(0) == null) {
            for (int i = 1; i < 101; ++i) {
                if (counter.getOrDefault(i, 0) > 0) {
                    ans -= 2 * i;
                    break;
                }
            }
        }
        return ans;
    }
}