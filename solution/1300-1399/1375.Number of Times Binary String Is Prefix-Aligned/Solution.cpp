class Solution {
public:
    int numTimesAllBlue(vector<int>& flips) {
        int ans = 0, mx = 0;
        for (int i = 1; i <= flips.size(); ++i) {
            mx = max(mx, flips[i - 1]);
            ans += mx == i;
        }
        return ans;
    }
};