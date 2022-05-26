class Solution {
public:
    int sumOfDigits(vector<int>& nums) {
        int x = nums[0];
        for (int& v : nums) x = min(x, v);
        int s = 0;
        for (; x != 0; x /= 10) s += x % 10;
        return 1 - s % 2;
    }
};