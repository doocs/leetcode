class Solution {
public:
    double mincostToHireWorkers(vector<int>& quality, vector<int>& wage, int k) {
        int n = quality.size();
        vector<pair<double, int>> t(n);
        for (int i = 0; i < n; ++i) {
            t[i] = {(double) wage[i] / quality[i], quality[i]};
        }
        sort(t.begin(), t.end());
        priority_queue<int> pq;
        double ans = 1e9;
        int tot = 0;
        for (auto& [x, q] : t) {
            tot += q;
            pq.push(q);
            if (pq.size() == k) {
                ans = min(ans, tot * x);
                tot -= pq.top();
                pq.pop();
            }
        }
        return ans;
    }
};