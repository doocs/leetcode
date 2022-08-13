class Solution {
public:
    long long singleDivisorTriplet(vector<int>& nums) {
        vector<int> counter(101);
        for (int& x : nums) ++counter[x];
        long long ans = 0;
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                for (int k = 1; k <= 100; ++k) {
                    int cnt1 = counter[i], cnt2 = counter[j], cnt3 = counter[k];
                    int s = i + j + k;
                    int cnt = (s % i == 0) + (s % j == 0) + (s % k == 0);
                    if (cnt != 1) continue;
                    if (i == j)
                        ans += 1ll * cnt1 * (cnt1 - 1) * cnt3;
                    else if (i == k)
                        ans += 1ll * cnt1 * (cnt1 - 1) * cnt2;
                    else if (j == k)
                        ans += 1ll * cnt1 * cnt2 * (cnt2 - 1);
                    else
                        ans += 1ll * cnt1 * cnt2 * cnt3;
                }
            }
        }
        return ans;
    }
};