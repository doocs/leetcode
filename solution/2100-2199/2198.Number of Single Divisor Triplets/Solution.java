class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] counter = new int[101];
        for (int x : nums) {
            ++counter[x];
        }
        long ans = 0;
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                for (int k = 1; k <= 100; ++k) {
                    int cnt1 = counter[i], cnt2 = counter[j], cnt3 = counter[k];
                    int s = i + j + k;
                    int cnt = 0;
                    if (s % i == 0) {
                        ++cnt;
                    }
                    if (s % j == 0) {
                        ++cnt;
                    }
                    if (s % k == 0) {
                        ++cnt;
                    }
                    if (cnt != 1) {
                        continue;
                    }
                    if (i == j) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt3;
                    } else if (i == k) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt2;
                    } else if (j == k) {
                        ans += (long) cnt1 * cnt2 * (cnt2 - 1);
                    } else {
                        ans += (long) cnt1 * cnt2 * cnt3;
                    }
                }
            }
        }
        return ans;
    }
}