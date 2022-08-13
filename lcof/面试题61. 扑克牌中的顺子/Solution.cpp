class Solution {
public:
    bool isStraight(vector<int>& nums) {
        if (nums.size() != 5) {
            return false;
        }

        std::sort(nums.begin(), nums.end());
        int zeroNum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] != 0) {
                // 这题的用例中，会出现超过两个0的情况
                break;
            }
            zeroNum++;
        }

        for (int i = zeroNum; i < nums.size() - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return false;
            }
        }

        return nums[4] - nums[zeroNum] <= 4;
    }
};
