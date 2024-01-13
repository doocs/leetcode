class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans(n);
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
};