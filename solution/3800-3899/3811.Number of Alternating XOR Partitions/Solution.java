class Solution {
    public int alternatingXOR(int[] nums, int target1, int target2) {
        final int mod = (int) 1e9 + 7;

        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Integer> cnt2 = new HashMap<>();
        cnt2.put(0, 1);

        int ans = 0;
        int pre = 0;
        for (int x : nums) {
            pre ^= x;
            int a = cnt2.getOrDefault(pre ^ target1, 0);
            int b = cnt1.getOrDefault(pre ^ target2, 0);
            ans = (a + b) % mod;
            cnt1.put(pre, (cnt1.getOrDefault(pre, 0) + a) % mod);
            cnt2.put(pre, (cnt2.getOrDefault(pre, 0) + b) % mod);
        }

        return ans;
    }
}
