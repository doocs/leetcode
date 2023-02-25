class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.count(y)) {
                return {m[y], i};
            }
            m[x] = i;
        }
    }
};