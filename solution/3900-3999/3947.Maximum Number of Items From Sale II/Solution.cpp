class Solution {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size(), mx = 0, mn = INT_MAX;

        for (auto& a : items) {
            mx = max(mx, a[0]);
            mn = min(mn, a[1]);
        }

        vector<int> freq(mx + 1), cnt(mx + 1);

        for (auto& a : items)
            freq[a[0]]++;

        for (int i = 1; i <= mx; i++) {
            for (int j = i; j <= mx; j += i)
                cnt[i] += freq[j];
            cnt[i]--;
        }

        vector<pair<int, int>> deals;

        for (auto& a : items) {
            if (cnt[a[0]] > 0 && a[1] <= 2 * mn)
                deals.push_back({a[1], cnt[a[0]]});
        }

        sort(deals.begin(), deals.end());

        long long ans = 0;

        for (auto [p, c] : deals) {
            int take = min(c, budget / p);
            ans += 2LL * take;
            budget -= take * p;
        }

        return ans + budget / mn;
    }
};
