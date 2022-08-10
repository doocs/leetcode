class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> m;
        for (int i = 0; i < nums.size(); ++i) {
            int v = nums[i];
            int x = target - v;
            if (m.count(x)) return {m[x], i};
            m[v] = i;
        }
        return {};
    }
};