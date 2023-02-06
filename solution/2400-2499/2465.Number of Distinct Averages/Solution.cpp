class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        unordered_set<int> s;
        for (int i = 0; i<n> > 1; ++i) s.insert(nums[i] + nums[n - i - 1]);
        return s.size();
    }
};