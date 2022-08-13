class Solution {
public:
    int maxPerformance(int n, vector<int>& speed, vector<int>& efficiency, int k) {
        vector<pair<int, int>> team;
        for (int i = 0; i < n; ++i) team.push_back({-efficiency[i], speed[i]});
        sort(team.begin(), team.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0;
        int mod = 1e9 + 7;
        long long t = 0;
        for (auto& x : team) {
            int s = x.second, e = -x.first;
            t += s;
            ans = max(ans, e * t);
            q.push(s);
            if (q.size() >= k) {
                t -= q.top();
                q.pop();
            }
        }
        return (int)(ans % mod);
    }
};