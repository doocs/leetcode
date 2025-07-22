class Solution {
public:
    int maximumRobots(vector<int>& chargeTimes, vector<int>& runningCosts, long long budget) {
        deque<int> q;
        long long s = 0;
        int ans = 0;
        int n = chargeTimes.size();
        for (int l = 0, r = 0; r < n; ++r) {
            s += runningCosts[r];
            while (q.size() && chargeTimes[q.back()] <= chargeTimes[r]) {
                q.pop_back();
            }
            q.push_back(r);
            while (q.size() && (r - l + 1) * s + chargeTimes[q.front()] > budget) {
                if (q.front() == l) {
                    q.pop_front();
                }
                s -= runningCosts[l++];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
