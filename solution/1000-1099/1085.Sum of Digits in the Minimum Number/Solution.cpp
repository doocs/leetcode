class Solution {
public:
    int sumOfDigits(vector<int>& nums) {
        int x = *min_element(nums.begin(), nums.end());
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
};