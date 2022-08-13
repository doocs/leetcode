class Solution {
public:
    int findGCD(vector<int>& nums) {
        int a = 0, b = 1000;
        for (int num : nums) {
            a = max(a, num);
            b = min(b, num);
        }
        return gcd(a, b);
    }
};