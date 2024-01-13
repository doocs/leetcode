class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> d;
        for (auto& w : words) ++d[w];
        vector<int> ans;
        int n = s.size(), m = words.size(), k = words[0].size();
        for (int i = 0; i < k; ++i) {
            int cnt = 0;
            unordered_map<string, int> t;
            for (int j = i; j <= n; j += k) {
                if (j - i >= m * k) {
                    auto s1 = s.substr(j - m * k, k);
                    --t[s1];
                    cnt -= d[s1] > t[s1];
                }
                auto s2 = s.substr(j, k);
                ++t[s2];
                cnt += d[s2] >= t[s2];
                if (cnt == m) ans.emplace_back(j - (m - 1) * k);
            }
        }
        return ans;
    }
};