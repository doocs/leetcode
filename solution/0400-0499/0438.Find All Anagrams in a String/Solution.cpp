class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        int m = s.size(), n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt1(26);
        for (char& c : p) {
            ++cnt1[c - 'a'];
        }
        vector<int> cnt2(26);
        for (int i = 0; i < n - 1; ++i) {
            ++cnt2[s[i] - 'a'];
        }
        for (int i = n - 1; i < m; ++i) {
            ++cnt2[s[i] - 'a'];
            if (cnt1 == cnt2) {
                ans.push_back(i - n + 1);
            }
            --cnt2[s[i - n + 1] - 'a'];
        }
        return ans;
    }
};