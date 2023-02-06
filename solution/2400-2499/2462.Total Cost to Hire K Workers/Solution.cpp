using pii = pair<int, int>;

class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int candidates) {
        priority_queue<pii, vector<pii>, greater<pii>> q;
        int n = costs.size();
        int i = candidates - 1, j = n - candidates;
        for (int h = 0; h < candidates; ++h) q.push({costs[h], h});
        for (int h = n - candidates; h < n; ++h)
            if (h > i) q.push({costs[h], h});
        long long ans = 0;
        while (k--) {
            auto [c, x] = q.top();
            q.pop();
            ans += c;
            if (x <= i) {
                if (++i < j) {
                    q.push({costs[i], i});
                }
            }
            if (x >= j) {
                if (--j > i) {
                    q.push({costs[j], j});
                }
            }
        }
        return ans;
    }
};