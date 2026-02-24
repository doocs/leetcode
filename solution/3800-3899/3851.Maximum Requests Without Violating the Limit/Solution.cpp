class Solution {
public:
    int maxRequests(vector<vector<int>>& requests, int k, int window) {
        unordered_map<int, vector<int>> g;
        for (auto& r : requests) {
            g[r[0]].push_back(r[1]);
        }

        int ans = requests.size();
        for (auto& [_, ts] : g) {
            sort(ts.begin(), ts.end());
            queue<int> kept;
            int deletions = 0;

            for (int t : ts) {
                while (!kept.empty() && t - kept.front() > window) {
                    kept.pop();
                }
                if (kept.size() < k) {
                    kept.push(t);
                } else {
                    ans--;
                }
            }
        }
        return ans;
    }
};
