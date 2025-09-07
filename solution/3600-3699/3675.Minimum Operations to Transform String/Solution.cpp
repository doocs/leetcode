class Solution {
public:
    int minOperations(string s) {
        int ans = 0;
        for (char c : s) {
            if (c != 'a') {
                ans = max(ans, 26 - (c - 'a'));
            }
        }
        return ans;
    }
};
