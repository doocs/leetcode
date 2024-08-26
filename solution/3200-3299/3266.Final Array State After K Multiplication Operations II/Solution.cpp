class Solution {
public:
    vector<int> getFinalState(vector<int>& nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }

        using ll = long long;
        using pli = pair<ll, int>;
        auto cmp = [](const pli& a, const pli& b) {
            if (a.first == b.first) {
                return a.second > b.second;
            }
            return a.first > b.first;
        };
        priority_queue<pli, vector<pli>, decltype(cmp)> pq(cmp);

        int n = nums.size();
        int m = *max_element(nums.begin(), nums.end());

        for (int i = 0; i < n; ++i) {
            pq.emplace(nums[i], i);
        }

        while (k > 0 && pq.top().first < m) {
            auto p = pq.top();
            pq.pop();
            p.first *= multiplier;
            pq.emplace(p);
            --k;
        }

        auto qpow = [&](ll a, ll n, ll mod) {
            ll ans = 1 % mod;
            a = a % mod;
            while (n > 0) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
                n >>= 1;
            }
            return ans;
        };

        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            auto p = pq.top();
            pq.pop();
            long long x = p.first;
            int j = p.second;
            nums[j] = static_cast<int>((x % mod) * qpow(multiplier, k / n + (i < k % n ? 1 : 0), mod) % mod);
        }

        return nums;
    }
};
