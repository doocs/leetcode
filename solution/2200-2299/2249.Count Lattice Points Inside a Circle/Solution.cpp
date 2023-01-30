class Solution {
public:
    int countLatticePoints(vector<vector<int>>& circles) {
        int mx = 0, my = 0;
        for (auto& c : circles) {
            mx = max(mx, c[0] + c[2]);
            my = max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (auto& c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};