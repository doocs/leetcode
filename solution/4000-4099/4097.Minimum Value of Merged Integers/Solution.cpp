class Solution {
public:
    int minMergedInteger(vector<int>& nums) {
        int ans = INT_MAX;
        for (int i = 0; i < (int) nums.size(); i += 2) {
            int v = stoi(to_string(nums[i]) + to_string(nums[i + 1]));
            ans = min(ans, v);
        }
        return ans;
    }
};