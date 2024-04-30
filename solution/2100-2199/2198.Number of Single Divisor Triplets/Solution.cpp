class Solution {
public:
    long long singleDivisorTriplet(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        long long ans = 0;
        for (int a = 1; a <= 100; ++a) {
            for (int b = 1; b <= 100; ++b) {
                for (int c = 1; c <= 100; ++c) {
                    int s = a + b + c;
                    int x = cnt[a], y = cnt[b], z = cnt[c];
                    int t = (s % a == 0) + (s % b == 0) + (s % c == 0);
                    if (t == 1) {
                        if (a == b) {
                            ans += 1LL * x * (x - 1) * z;
                        } else if (a == c) {
                            ans += 1LL * x * (x - 1) * y;
                        } else if (b == c) {
                            ans += 1LL * x * y * (y - 1);
                        } else {
                            ans += 1LL * x * y * z;
                        }
                    }
                }
            }
        }
        return ans;
    }
};