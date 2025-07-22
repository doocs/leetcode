class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int l = 1, r = ranges::max(nums);
        while (l < r) {
            int mid = (l + r) >> 1;
            long long s = 0;
            for (int x : nums) {
                s += (x - 1) / mid;
            }
            if (s <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
