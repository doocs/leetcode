class Solution {
public:
    int diagonalPrime(vector<vector<int>>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (isPrime(nums[i][i])) {
                ans = max(ans, nums[i][i]);
            }
            if (isPrime(nums[i][n - i - 1])) {
                ans = max(ans, nums[i][n - i - 1]);
            }
        }
        return ans;
    }

    bool isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
};