class Solution {
public:
    int minimumDeletions(string s) {
        int ans = 0, b = 0;
        for (char c : s) {
            if (c == 'b') {
                ++b;
            } else {
                ans = min(b, ans + 1);
            }
        }
        return ans;
    }
};