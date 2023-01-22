class Solution {
public:
    int alternateDigitSum(int n) {
        int ans = 0, sign = 1;
        for (char c : to_string(n)) {
            int x = c - '0';
            ans += sign * x;
            sign *= -1;
        }
        return ans;
    }
};