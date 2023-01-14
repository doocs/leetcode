class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        unordered_map<int, int> d;
        for (int i = 0; i < nums.size(); ++i) {
            d[nums[i]] = i;
        }
        for (auto& op : operations) {
            int a = op[0], b = op[1];
            nums[d[a]] = b;
            d[b] = d[a];
        }
        return nums;
    }
};