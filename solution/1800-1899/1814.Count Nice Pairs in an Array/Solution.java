class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int y = x - rev(x);
            cnt.put(y, cnt.getOrDefault(y, 0) + 1);
        }
        long ans = 0;
        for (int v : cnt.values()) {
            ans = (ans + (long) v * (v - 1) / 2) % MOD;
        }
        return (int) ans;
    }

    private int rev(int x) {
        int y = 0;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
}