class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> map;
        for (int i = 0; i < nums.size(); ++i) {
            int num = target - nums[i];
            if (map.find(num) != map.end()) {
                return {map[num], i};
            }
            map[nums[i]] = i;
        }
        return {};
    }
};