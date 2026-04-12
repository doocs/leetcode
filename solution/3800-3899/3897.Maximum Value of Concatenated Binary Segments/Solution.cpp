class Solution {
public:
    static constexpr int MOD = 1'000'000'007;

    int maxValue(vector<int>& nums1, vector<int>& nums0) {
        vector<pair<int, int>> pairs;
        int b = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            pairs.emplace_back(nums1[i], nums0[i]);
            b += nums1[i] + nums0[i];
        }

        sort(pairs.begin(), pairs.end(), [](const auto& a, const auto& b) {
            auto group = [](const pair<int, int>& p) {
                if (p.second == 0) {
                    return 0;
                }
                if (p.first > 0) {
                    return 1;
                }
                return 2;
            };

            int g1 = group(a), g2 = group(b);
            if (g1 != g2) {
                return g1 < g2;
            }
            if (g1 == 0) {
                return a.first > b.first;
            }
            if (g1 == 1) {
                if (a.first != b.first) {
                    return a.first > b.first;
                }
                return a.second < b.second;
            }
            return a.second < b.second;
        });

        vector<long long> p(b, 1);
        for (int i = 1; i < b; ++i) {
            p[i] = p[i - 1] * 2 % MOD;
        }

        long long ans = 0;
        --b;
        for (auto& [cnt1, cnt0] : pairs) {
            while (cnt1--) {
                ans = (ans + p[b--]) % MOD;
            }
            b -= cnt0;
        }
        return (int) ans;
    }
};
