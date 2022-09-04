class Solution {
public:
    int maximumRobots(vector<int>& chargeTimes, vector<int>& runningCosts, long long budget) {
        deque<int> q;
        long long s = 0;
        int ans = 0, j = 0, n = chargeTimes.size();
        for (int i = 0; i < n; ++i) {
            int a = chargeTimes[i], b = runningCosts[i];
            while (!q.empty() && chargeTimes[q.back()] <= a) q.pop_back();
            q.push_back(i);
            s += b;
            while (!q.empty() && chargeTimes[q.front()] + (i - j + 1) * s > budget) {
                if (q.front() == j) {
                    q.pop_front();
                }
                s -= runningCosts[j++];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};