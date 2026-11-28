class Solution {
public:
    long long maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size();
        vector<int> freq(n + 1, 0);
        int min_price = 2e9;

        for (const auto& item : items) {
            freq[item[0]]++;
            min_price = min(min_price, item[1]);
        }

        vector<int> div_count(n + 1, 0);
        for (int f = 1; f <= n; ++f) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                div_count[f] += freq[m];
            }
        }

        vector<pair<int, int>> sorted_items;
        sorted_items.reserve(n);
        for (const auto& item : items) {
            sorted_items.push_back({item[1], div_count[item[0]] - 1});
        }
        sort(sorted_items.begin(), sorted_items.end());

        long long rem = budget;
        long long ans = 0;

        for (const auto& [price, gain] : sorted_items) {
            if (price >= 2LL * min_price) break;
            if (gain <= 0) continue;

            long long can_buy = min((long long) gain, rem / price);
            ans += can_buy * 2;
            rem -= can_buy * price;

            if (can_buy < gain) break;
        }

        ans += rem / min_price;
        return ans;
    }
};