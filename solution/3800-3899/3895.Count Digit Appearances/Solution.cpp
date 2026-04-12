class Solution {
public:
    int countDigitOccurrences(vector<int>& nums, int digit) {
        int ans = 0;
        for (int x : nums) {
            for (; x > 0; x /= 10) {
                if (x % 10 == digit) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
