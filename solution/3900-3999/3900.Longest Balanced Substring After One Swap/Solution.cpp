class Solution {
public:
    int longestBalanced(string s) {
        int cnt0 = count(s.begin(), s.end(), '0');
        int cnt1 = s.size() - cnt0;
        unordered_map<int, vector<int>> pos;
        pos[0] = {-1};
        int ans = 0, pre = 0;
        for (int i = 0; i < s.size(); ++i) {
            pre += s[i] == '1' ? 1 : -1;
            pos[pre].push_back(i);

            ans = max(ans, i - pos[pre][0]);
            if (pos.contains(pre - 2)) {
                auto& p = pos[pre - 2];
                if ((i - p[0] - 2) / 2 < cnt0) {
                    ans = max(ans, i - p[0]);
                } else if (p.size() > 1) {
                    ans = max(ans, i - p[1]);
                }
            }

            if (pos.contains(pre + 2)) {
                auto& p = pos[pre + 2];
                if ((i - p[0] - 2) / 2 < cnt1) {
                    ans = max(ans, i - p[0]);
                } else if (p.size() > 1) {
                    ans = max(ans, i - p[1]);
                }
            }
        }
        return ans;
    }
};