class Solution {
public:
    int minimumCost(int m, int n, vector<int>& horizontalCut, vector<int>& verticalCut) {
        sort(horizontalCut.rbegin(), horizontalCut.rend());
        sort(verticalCut.rbegin(), verticalCut.rend());
        int ans = 0;
        int i = 0, j = 0;
        int h = 1, v = 1;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i++] * v;
                h++;
            } else {
                ans += verticalCut[j++] * h;
                v++;
            }
        }
        return ans;
    }
};