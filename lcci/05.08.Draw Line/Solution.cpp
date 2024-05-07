class Solution {
public:
    vector<int> drawLine(int length, int w, int x1, int x2, int y) {
        vector<int> ans(length);
        int i = (y * w + x1) / 32;
        int j = (y * w + x2) / 32;
        for (int k = i; k <= j; ++k) {
            ans[k] = -1;
        }
        ans[i] = ans[i] & unsigned(-1) >> (x1 % 32);
        ans[j] = ans[j] & unsigned(-1) << (31 - x2 % 32);
        return ans;
    }
};