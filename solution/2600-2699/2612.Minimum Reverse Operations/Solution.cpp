class Solution {
public:
    vector<int> minReverseOperations(int n, int p, vector<int>& banned, int k) {
        vector<int> ans(n, -1);
        ans[p] = 0;
        set<int> ts[2];
        for (int i = 0; i < n; ++i) {
            ts[i % 2].insert(i);
        }
        ts[p % 2].erase(p);
        for (int i : banned) {
            ts[i % 2].erase(i);
        }
        ts[0].insert(n);
        ts[1].insert(n);
        queue<int> q{{p}};
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            int mi = max(i - k + 1, k - i - 1);
            int mx = min(i + k - 1, n * 2 - k - i - 1);
            auto& s = ts[mi % 2];
            auto it = s.lower_bound(mi);
            while (*it <= mx) {
                int j = *it;
                ans[j] = ans[i] + 1;
                q.push(j);
                it = s.erase(it);
            }
        }
        return ans;
    }
};