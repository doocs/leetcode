class Solution {
public:
    string filterCharacters(string s, int k) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        for (char c : s) {
            if (cnt[c - 'a'] < k) {
                ans.push_back(c);
            }
        }
        return ans;
    }
};
