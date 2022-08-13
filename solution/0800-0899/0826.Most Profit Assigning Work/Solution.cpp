class Solution {
public:
    int maxProfitAssignment(vector<int>& difficulty, vector<int>& profit, vector<int>& worker) {
        int n = difficulty.size();
        vector<pair<int, int>> job;
        for (int i = 0; i < n; ++i) {
            job.push_back({difficulty[i], profit[i]});
        }
        sort(job.begin(), job.end());
        sort(worker.begin(), worker.end());
        int i = 0, t = 0;
        int res = 0;
        for (auto w : worker) {
            while (i < n && job[i].first <= w) {
                t = max(t, job[i++].second);
            }
            res += t;
        }
        return res;
    }
};