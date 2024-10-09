class Solution {
public:
    int getSum(vector<int>& nums) {
        using ll = long long;
        const int mod = 1e9 + 7;
        auto calc = [&](const vector<int>& nums) -> ll {
            int n = nums.size();
            vector<ll> left(n), right(n);
            unordered_map<int, ll> cnt;

            for (int i = 1; i < n; ++i) {
                cnt[nums[i - 1]] += 1 + cnt[nums[i - 1] - 1];
                left[i] = cnt[nums[i] - 1];
            }

            cnt.clear();

            for (int i = n - 2; i >= 0; --i) {
                cnt[nums[i + 1]] += 1 + cnt[nums[i + 1] + 1];
                right[i] = cnt[nums[i] + 1];
            }

            ll ans = 0;
            for (int i = 0; i < n; ++i) {
                ans = (ans + (left[i] + right[i] + left[i] * right[i] % mod) * nums[i] % mod) % mod;
            }
            return ans;
        };

        ll x = calc(nums);
        reverse(nums.begin(), nums.end());
        ll y = calc(nums);
        ll s = accumulate(nums.begin(), nums.end(), 0LL);
        return static_cast<int>((x + y + s) % mod);
    }
};
