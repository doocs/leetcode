class Solution {
public:
    vector<int> maximumBobPoints(int numArrows, vector<int>& aliceArrows) {
        int st = 0, mx = 0;
        int m = aliceArrows.size();
        for (int mask = 1; mask < 1 << m; ++mask) {
            int cnt = 0, s = 0;
            for (int i = 0; i < m; ++i) {
                if (mask >> i & 1) {
                    s += i;
                    cnt += aliceArrows[i] + 1;
                }
            }
            if (cnt <= numArrows && s > mx) {
                mx = s;
                st = mask;
            }
        }
        vector<int> ans(m);
        for (int i = 0; i < m; ++i) {
            if (st >> i & 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
};
