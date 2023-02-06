class Solution {
public:
    bool isGoodArray(vector<int>& nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }
};