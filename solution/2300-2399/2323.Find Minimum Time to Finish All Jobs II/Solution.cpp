class Solution {
public:
    int minimumTime(vector<int>& jobs, vector<int>& workers) {
        sort(jobs.begin(), jobs.end());
        sort(workers.begin(), workers.end());
        int ans = 0;
        for (int i = 0; i < jobs.size(); ++i) ans = max(ans, (jobs[i] + workers[i] - 1) / workers[i]);
        return ans;
    }
};