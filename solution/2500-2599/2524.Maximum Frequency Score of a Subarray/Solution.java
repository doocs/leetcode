class Solution {
    public int maxFrequencyScore(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }
        long cur = 0;
        for (var e : cnt.entrySet()) {
            cur = (cur + qmi(e.getKey(), e.getValue(), mod)) % mod;
        }
        long ans = cur;
        for (int i = k; i < nums.length; ++i) {
            int a = nums[i - k];
            int b = nums[i];
            if (a != b) {
                if (cnt.getOrDefault(b, 0) > 0) {
                    cur += (b - 1) * qmi(b, cnt.get(b), mod) % mod;
                } else {
                    cur += b;
                }
                if (cnt.getOrDefault(a, 0) > 1) {
                    cur -= (a - 1) * qmi(a, cnt.get(a) - 1, mod) % mod;
                } else {
                    cur -= a;
                }
                cur = (cur + mod) % mod;
                cnt.put(b, cnt.getOrDefault(b, 0) + 1);
                cnt.put(a, cnt.getOrDefault(a, 0) - 1);
                ans = Math.max(ans, cur);
            }
        }
        return (int) ans;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}