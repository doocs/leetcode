class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.size();
        int first[26], last[26];
        memset(first, -1, sizeof(first));
        for (int i = 0; i < n; ++i) {
            int x = s[i] - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        vector<pair<int, int>> segs;
        for (int x = 0; x < 26; ++x) {
            if (first[x] == -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i <= r; ++i) {
                int y = s[i] - 'a';
                if (first[y] < l) {
                    break;
                }
                r = max(r, last[y]);
            }
            if (i > r) {
                segs.emplace_back(l, r);
            }
        }
        sort(segs.begin(), segs.end(), [](const auto& a, const auto& b) {
            return a.second < b.second;
        });
        vector<string> ans;
        int end = -1;
        for (auto [l, r] : segs) {
            if (l > end) {
                ans.emplace_back(s.substr(l, r - l + 1));
                end = r;
            }
        }
        return ans;
    }
};
