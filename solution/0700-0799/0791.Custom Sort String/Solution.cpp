class Solution {
public:
    string customSortString(string order, string s) {
        vector<int> cnt(26);
        for (char c : s) ++cnt[c - 'a'];
        string ans = "";
        for (char c : order) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        for (char c : s) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        return ans;
    }
};