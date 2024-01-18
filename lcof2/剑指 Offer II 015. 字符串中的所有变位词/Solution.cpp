class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        int m = s.size();
        int n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt1(26), cnt2(26);
        for (int i = 0; i < n; ++i) {
            ++cnt1[s[i] - 'a'];
            ++cnt2[p[i] - 'a'];
        }
        if (cnt1 == cnt2) {
            ans.push_back(0);
        }
        for (int i = n; i < m; ++i) {
            ++cnt1[s[i] - 'a'];
            --cnt1[s[i - n] - 'a'];
            if (cnt1 == cnt2) {
                ans.push_back(i - n + 1);
            }
        }
        return ans;
    }
};