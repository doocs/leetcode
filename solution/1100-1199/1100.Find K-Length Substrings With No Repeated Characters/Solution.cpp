class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int ans = 0;
        unordered_map<int, int> mp;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            char c = s[i];
            if (mp.count(c)) j = max(j, mp[c] + 1);
            mp[c] = i;
            if (i - j + 1 >= k) ++ans;
        }
        return ans;
    }
};