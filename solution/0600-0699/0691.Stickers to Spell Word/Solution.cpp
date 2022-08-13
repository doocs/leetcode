class Solution {
public:
    int minStickers(vector<string>& stickers, string target) {
        queue<int> q {{0}};
        int ans = 0;
        int n = target.size();
        vector<bool> vis(1 << n);
        vis[0] = true;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int state = q.front();
                if (state == (1 << n) - 1) return ans;
                q.pop();
                for (auto& s : stickers) {
                    int nxt = state;
                    vector<int> cnt(26);
                    for (char& c : s) ++cnt[c - 'a'];
                    for (int i = 0; i < n; ++i) {
                        int idx = target[i] - 'a';
                        if (!(nxt & (1 << i)) && cnt[idx]) {
                            nxt |= 1 << i;
                            --cnt[idx];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};