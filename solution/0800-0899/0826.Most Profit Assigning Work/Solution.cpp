class Solution {
public:
    int maxProfitAssignment(vector<int>& difficulty, vector<int>& profit, vector<int>& worker) {
        sort(worker.begin(), worker.end());
        int n = profit.size();
        vector<pair<int, int>> jobs;
        for (int i = 0; i < n; ++i) {
            jobs.emplace_back(difficulty[i], profit[i]);
        }
        sort(jobs.begin(), jobs.end());
        int ans = 0, mx = 0, i = 0;
        for (int w : worker) {
            while (i < n && jobs[i].first <= w) {
                mx = max(mx, jobs[i++].second);
            }
            ans += mx;
        }
        return ans;
    }
};