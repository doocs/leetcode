class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int len = nums.size();
        if (len == 0) return 1;
        int i = 0;
        while (nums[i] <= 0 && i < len) i++;
        if (i == len) return 1;

        int tmp = 1;
        while (i < len) {
            if (nums[i] != tmp) return tmp;
            while (len > i + 1 && nums[i] == nums[i + 1]) i++; //去重
            i++;
            tmp++;
        }
        return tmp;
    }
};