class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v % k == 0) {
                ans += x * v;
            }
        }
        return ans;
    }
}
