class Solution {
public:
    long long maximumSubsequenceCount(string text, string pattern) {
        long long ans = 0;
        int x = 0, y = 0;
        for (char& c : text) {
            if (c == pattern[1]) {
                ++y;
                ans += x;
            }
            if (c == pattern[0]) {
                ++x;
            }
        }
        ans += max(x, y);
        return ans;
    }
};
