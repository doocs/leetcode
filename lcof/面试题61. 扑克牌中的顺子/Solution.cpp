class Solution {
public:
    bool isStraight(vector<int>& nums) {
        bool vis[14]{};
        int mi = 20, mx = -1;
        for (int& x : nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = min(mi, x);
            mx = max(mx, x);
        }
        return mx - mi <= 4;
    }
};