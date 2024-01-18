class Solution {
public:
    vector<int> minInterval(vector<vector<int>>& intervals, vector<int>& queries) {
        int n = intervals.size(), m = queries.size();
        sort(intervals.begin(), intervals.end());
        using pii = pair<int, int>;
        vector<pii> qs;
        for (int i = 0; i < m; ++i) {
            qs.emplace_back(queries[i], i);
        }
        sort(qs.begin(), qs.end());
        vector<int> ans(m, -1);
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        int i = 0;
        for (auto& [x, j] : qs) {
            while (i < n && intervals[i][0] <= x) {
                int a = intervals[i][0], b = intervals[i][1];
                pq.emplace(b - a + 1, b);
                ++i;
            }
            while (!pq.empty() && pq.top().second < x) {
                pq.pop();
            }
            if (!pq.empty()) {
                ans[j] = pq.top().first;
            }
        }
        return ans;
    }
};