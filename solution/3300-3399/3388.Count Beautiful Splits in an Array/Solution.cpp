class Solution {
public:
    int beautifulSplits(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1, 0));

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                bool a = (i <= j - i) && (lcp[0][i] >= i);
                bool b = (j - i <= n - j) && (lcp[i][j] >= j - i);
                if (a || b) {
                    ans++;
                }
            }
        }

        return ans;
    }
};
