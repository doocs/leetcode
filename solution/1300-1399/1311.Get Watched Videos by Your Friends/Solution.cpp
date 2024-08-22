class Solution {
public:
    vector<string> watchedVideosByFriends(vector<vector<string>>& watchedVideos, vector<vector<int>>& friends, int id, int level) {
        queue<int> q{{id}};
        int n = friends.size();
        vector<bool> vis(n);
        vis[id] = true;
        while (level--) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                for (int j : friends[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
        unordered_map<string, int> cnt;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (const auto& v : watchedVideos[i]) {
                cnt[v]++;
            }
        }
        vector<string> ans;
        for (const auto& [key, _] : cnt) {
            ans.push_back(key);
        }
        sort(ans.begin(), ans.end(), [&cnt](const string& a, const string& b) {
            return cnt[a] == cnt[b] ? a < b : cnt[a] < cnt[b];
        });
        return ans;
    }
};
