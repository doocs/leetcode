class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt.getOrDefault(x, 0) > 0) {
                int m = Math.min(cnt.get(x), k);
                cnt.merge(x, -m, Integer::sum);
                cnt.merge(-x, m, Integer::sum);
                k -= m;
            }
        }
        if ((k & 1) == 1 && cnt.getOrDefault(0, 0) == 0) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt.getOrDefault(x, 0) > 0) {
                    cnt.merge(x, -1, Integer::sum);
                    cnt.merge(-x, 1, Integer::sum);
                    break;
                }
            }
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            ans += e.getKey() * e.getValue();
        }
        return ans;
    }
}