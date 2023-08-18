class Solution {
public:
    vector<long long> minimumRelativeLosses(vector<int>& prices, vector<vector<int>>& queries) {
        int n = prices.size();
        sort(prices.begin(), prices.end());
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + prices[i - 1];
        }
        auto f = [&](int k, int m) {
            int l = 0, r = upper_bound(prices.begin(), prices.end(), k) - prices.begin();
            r = min(r, m);
            while (l < r) {
                int mid = (l + r) >> 1;
                int right = m - mid;
                if (prices[mid] < 2LL * k - prices[n - right]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        };
        vector<long long> ans;
        for (auto& q : queries) {
            int k = q[0], m = q[1];
            int l = f(k, m);
            int r = m - l;
            ans.push_back(s[l] + 2LL * k * r - (s[n] - s[n - r]));
        }
        return ans;
    }
};