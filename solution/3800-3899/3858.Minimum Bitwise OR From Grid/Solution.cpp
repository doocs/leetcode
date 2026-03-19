class Solution {
public:
    int minimumOR(vector<vector<int>>& grid) {
        int mx = 0;
        for (auto& row : grid) {
            mx = max(mx, ranges::max(row));
        }

        int m = 32 - __builtin_clz(mx);
        int ans = 0;

        for (int i = m - 1; i >= 0; i--) {
            int mask = ans | ((1 << i) - 1);
            for (auto& row : grid) {
                bool found = false;
                for (int x : row) {
                    if ((x | mask) == mask) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ans |= 1 << i;
                    break;
                }
            }
        }

        return ans;
    }
};
