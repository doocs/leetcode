class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, int k) {
        using ll = long long;
        const int mod = 1e9 + 7;
        auto qpow = [&](ll a, ll n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
        }
        ll cur = 0;
        for (auto& [k, v] : cnt) {
            cur = (cur + qpow(k, v)) % mod;
        }
        ll ans = cur;
        for (int i = k; i < nums.size(); ++i) {
            int a = nums[i - k], b = nums[i];
            if (a != b) {
                cur += cnt[b] ? (b - 1) * qpow(b, cnt[b]) % mod : b;
                cur -= cnt[a] > 1 ? (a - 1) * qpow(a, cnt[a] - 1) % mod : a;
                cur = (cur + mod) % mod;
                ans = max(ans, cur);
                cnt[b]++;
                cnt[a]--;
            }
        }
        return ans;
    }
};