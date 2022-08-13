class Solution {
public:
    long long numberOfSubstrings(string s) {
        vector<int> counter(26);
        long long ans = 0;
        for (char c : s) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
        }
        return ans;
    }
};