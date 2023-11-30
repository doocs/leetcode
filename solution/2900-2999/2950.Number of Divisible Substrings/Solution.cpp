class Solution {
public:
    int countDivisibleSubstrings(string word) {
        string d[9] = {"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"};
        int mp[26]{};
        for (int i = 0; i < 9; ++i) {
            for (char& c : d[i]) {
                mp[c - 'a'] = i + 1;
            }
        }
        int ans = 0;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += mp[word[j] - 'a'];
                ans += s % (j - i + 1) == 0 ? 1 : 0;
            }
        }
        return ans;
    }
};