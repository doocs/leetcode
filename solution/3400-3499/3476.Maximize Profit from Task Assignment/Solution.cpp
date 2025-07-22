class Solution {
public:
    long long maxProfit(vector<int>& workers, vector<vector<int>>& tasks) {
        unordered_map<int, priority_queue<int>> d;
        for (const auto& t : tasks) {
            d[t[0]].push(t[1]);
        }
        long long ans = 0;
        for (int skill : workers) {
            if (d.contains(skill)) {
                auto& pq = d[skill];
                ans += pq.top();
                pq.pop();
                if (pq.empty()) {
                    d.erase(skill);
                }
            }
        }
        int mx = 0;
        for (const auto& [_, pq] : d) {
            mx = max(mx, pq.top());
        }
        ans += mx;
        return ans;
    }
};
