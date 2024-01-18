class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        unordered_set<int> s;
        int n = nums.size();
        for (int i = 0; i < n >> 1; ++i) {
            s.insert(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
};