class Solution {
public:
    int minCost(vector<int>& startPos, vector<int>& homePos, vector<int>& rowCosts, vector<int>& colCosts) {
        int i = startPos[0], j = startPos[1];
        int x = homePos[0], y = homePos[1];
        int ans = 0;
        if (i < x) {
            ans += accumulate(rowCosts.begin() + i + 1, rowCosts.begin() + x + 1, 0);
        } else {
            ans += accumulate(rowCosts.begin() + x, rowCosts.begin() + i, 0);
        }
        if (j < y) {
            ans += accumulate(colCosts.begin() + j + 1, colCosts.begin() + y + 1, 0);
        } else {
            ans += accumulate(colCosts.begin() + y, colCosts.begin() + j, 0);
        }
        return ans;
    }
};