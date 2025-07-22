class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        unordered_map<int, int> d;
        for (int i = 0; i < nums.size(); ++i) {
            d[nums[i]] = i;
        }
        for (auto& op : operations) {
            int x = op[0], y = op[1];
            nums[d[x]] = y;
            d[y] = d[x];
        }
        return nums;
    }
};