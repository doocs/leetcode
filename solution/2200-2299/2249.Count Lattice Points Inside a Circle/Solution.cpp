class Solution {
public:
    int countLatticePoints(vector<vector<int>>& circles) {
        int ans = 0;
        for (int i = 0; i <= 200; ++i) {
            for (int j = 0; j <= 200; ++j) {
                for (auto& c : circles) {
                    int x = c[0] - i, y = c[1] - j, r = c[2];
                    if (x * x + y * y <= r * r) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};