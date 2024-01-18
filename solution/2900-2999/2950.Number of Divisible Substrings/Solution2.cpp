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
        for (int i = 1; i < 10; ++i) {
            unordered_map<int, int> cnt{{0, 1}};
            int s = 0;
            for (char& c : word) {
                s += mp[c - 'a'] - i;
                ans += cnt[s]++;
            }
        }
        return ans;
    }
};