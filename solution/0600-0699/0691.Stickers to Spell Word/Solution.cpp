class Solution {
public:
    int minStickers(vector<string>& stickers, string target) {
        int n = target.size();
        queue<int> q{{0}};
        vector<bool> vis(1 << n);
        vis[0] = true;
        for (int ans = 0; q.size(); ++ans) {
            for (int m = q.size(); m; --m) {
                int cur = q.front();
                q.pop();
                if (cur == (1 << n) - 1) {
                    return ans;
                }
                for (auto& s : stickers) {
                    int cnt[26]{};
                    int nxt = cur;
                    for (char& c : s) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int j = target[i] - 'a';
                        if ((cur >> i & 1) == 0 && cnt[j] > 0) {
                            nxt |= 1 << i;
                            --cnt[j];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.push(nxt);
                    }
                }
            }
        }
        return -1;
    }
};