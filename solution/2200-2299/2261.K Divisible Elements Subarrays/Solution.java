class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        Set<Long> s = new HashSet<>();
        int n = nums.length;
        int base1 = 131, base2 = 13331;
        int mod1 = (int) 1e9 + 7, mod2 = (int) 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0 ? 1 : 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.add(h1 << 32 | h2);
            }
        }
        return s.size();
    }
}
