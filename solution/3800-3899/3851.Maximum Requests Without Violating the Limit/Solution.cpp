class Solution {
public:
    int maxRequests(vector<vector<int>>& requests, int k, int window) {
        unordered_map<int, vector<int>> g;
        g.reserve(requests.size() * 2);

        for (auto& r : requests) {
            g[r[0]].push_back(r[1]);
        }

        int ans = 0;
        deque<int> kept;

        for (auto& [_, ts] : g) {
            sort(ts.begin(), ts.end());
            kept.clear();
            int deletions = 0;

            for (int t : ts) {
                while (!kept.empty() && t - kept.front() > window) {
                    kept.pop_front();
                }
                kept.push_back(t);
                if (kept.size() > k) {
                    kept.pop_back();
                    deletions++;
                }
            }
            ans += ts.size() - deletions;
        }
        return ans;
    }
};
