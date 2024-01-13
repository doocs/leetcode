class Solution {
public:
    int secondsToRemoveOccurrences(string s) {
        int ans = 0, cnt = 0;
        for (char c : s) {
            if (c == '0') {
                ++cnt;
            } else if (cnt) {
                ans = max(ans + 1, cnt);
            }
        }
        return ans;
    }
};