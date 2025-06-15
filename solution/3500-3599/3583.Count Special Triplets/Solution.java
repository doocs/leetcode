class Solution {
    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (int x : nums) {
            right.merge(x, 1, Integer::sum);
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int x : nums) {
            right.merge(x, -1, Integer::sum);
            ans = (ans + 1L * left.getOrDefault(x * 2, 0) * right.getOrDefault(x * 2, 0) % mod)
                % mod;
            left.merge(x, 1, Integer::sum);
        }
        return (int) ans;
    }
}