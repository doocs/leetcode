class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& numsDivide) {
        int x = numsDivide[0];
        for (int i = 1; i < numsDivide.size(); ++i) x = gcd(x, numsDivide[i]);
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); ++i)
            if (x % nums[i] == 0) return i;
        return -1;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};