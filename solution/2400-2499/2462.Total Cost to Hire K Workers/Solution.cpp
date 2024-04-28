class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int candidates) {
        int n = costs.size();
        if (candidates * 2 > n) {
            sort(costs.begin(), costs.end());
            return accumulate(costs.begin(), costs.begin() + k, 0LL);
        }
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        for (int i = 0; i < candidates; ++i) {
            pq.emplace(costs[i], i);
            pq.emplace(costs[n - i - 1], n - i - 1);
        }
        long long ans = 0;
        int l = candidates, r = n - candidates - 1;
        while (k--) {
            auto [cost, i] = pq.top();
            pq.pop();
            ans += cost;
            if (l > r) {
                continue;
            }
            if (i < l) {
                pq.emplace(costs[l], l++);
            } else {
                pq.emplace(costs[r], r--);
            }
        }
        return ans;
    }
};