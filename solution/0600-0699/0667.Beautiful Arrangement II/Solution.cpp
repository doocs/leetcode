class Solution {
public:
    vector<int> constructArray(int n, int k) {
        int l = 1, r = n;
        vector<int> ans(n);
        for (int i = 0; i < k; ++i) {
            ans[i] = i % 2 == 0 ? l++ : r--;
        }
        for (int i = k; i < n; ++i) {
            ans[i] = k % 2 == 0 ? r-- : l++;
        }
        return ans;
    }
};