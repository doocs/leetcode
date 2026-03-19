class Solution {
public:
    int smallestBalancedIndex(vector<int>& nums) {
        long long s = 0, p = 1;
        for (int x : nums) {
            s += x;
        }
        for (int i = nums.size() - 1; i >= 0; --i) {
            s -= nums[i];
            if (s == p) {
                return i;
            }
            p *= nums[i];
            if (p >= s) {
                break;
            }
        }
        return -1;
    }
};
