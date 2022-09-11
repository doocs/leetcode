class Solution {
public:
    int maxPerformance(int n, vector<int>& speed, vector<int>& efficiency, int k) {
        vector<pair<int, int>> t(n);
        for (int i = 0; i < n; ++i) t[i] = {-efficiency[i], speed[i]};
        sort(t.begin(), t.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0, tot = 0;
        int mod = 1e9 + 7;
        for (auto& x : t) {
            int s = x.second, e = -x.first;
            tot += s;
            ans = max(ans, tot * e);
            q.push(s);
            if (q.size() == k) {
                tot -= q.top();
                q.pop();
            }
        }
        return (int) (ans % mod);
    }
};