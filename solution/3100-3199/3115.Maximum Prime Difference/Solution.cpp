class Solution {
public:
    int maximumPrimeDifference(vector<int>& nums) {
        for (int i = 0;; ++i) {
            if (isPrime(nums[i])) {
                for (int j = nums.size() - 1;; --j) {
                    if (isPrime(nums[j])) {
                        return j - i;
                    }
                }
            }
        }
    }

    bool isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / i; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
};