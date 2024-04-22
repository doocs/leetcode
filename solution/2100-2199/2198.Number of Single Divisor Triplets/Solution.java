class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = 0;
                    t += s % a == 0 ? 1 : 0;
                    t += s % b == 0 ? 1 : 0;
                    t += s % c == 0 ? 1 : 0;
                    if (t == 1) {
                        if (a == b) {
                            ans += 1L * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1L * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1L * x * y * (y - 1);
                        } else {
                            ans += 1L * x * y * z;
                        }
                    }
                }
            }
        }
        return ans;
    }
}