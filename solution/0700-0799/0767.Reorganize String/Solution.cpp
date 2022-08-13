class Solution {
public:
    string reorganizeString(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        int mx = *max_element(cnt.begin(), cnt.end());
        int n = s.size();
        if (mx > (n + 1) / 2) return "";
        vector<vector<int>> m;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) m.push_back({cnt[i], i});
        }
        sort(m.begin(), m.end());
        reverse(m.begin(), m.end());
        string ans = s;
        int k = 0;
        for (auto& e : m) {
            int v = e[0], i = e[1];
            while (v--) {
                ans[k] = 'a' + i;
                k += 2;
                if (k >= n) k = 1;
            }
        }
        return ans;
    }
};