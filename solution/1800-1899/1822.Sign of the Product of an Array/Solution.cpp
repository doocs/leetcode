class Solution {
public:
    int arraySign(vector<int>& nums) {
        int res = 1;
        for (auto& num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                res *= -1;
            }
        }
        return res;
    }
};