/**
 *  Author: Moriarty12138
 */
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int n = nums.size();
        if(n < 2) {
            return n;
        }

        int idx = 0;
        for(int n : nums) {
            if(idx < 1 || nums[idx-1] < n) {
                nums[idx++] = n;
            }
        }
        return idx;
    }
};
