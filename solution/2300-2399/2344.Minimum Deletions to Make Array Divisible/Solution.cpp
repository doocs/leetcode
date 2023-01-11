class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& numsDivide) {
        int x = 0;
        for (int& v : numsDivide) {
            x = gcd(x, v);
        }
        int y = 1 << 30;
        for (int& v : nums) {
            if (x % v == 0) {
                y = min(y, v);
            }
        }
        if (y == 1 << 30) {
            return -1;
        }
        int ans = 0;
        for (int& v : nums) {
            ans += v < y;
        }
        return ans;
    }
};