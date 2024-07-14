class Solution {
public:
    long long minimumCost(int m, int n, vector<int>& horizontalCut, vector<int>& verticalCut) {
        sort(horizontalCut.rbegin(), horizontalCut.rend());
        sort(verticalCut.rbegin(), verticalCut.rend());
        long long ans = 0;
        int i = 0, j = 0;
        int h = 1, v = 1;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
                ans += 1LL * horizontalCut[i++] * v;
                h++;
            } else {
                ans += 1LL * verticalCut[j++] * h;
                v++;
            }
        }
        return ans;
    }
};