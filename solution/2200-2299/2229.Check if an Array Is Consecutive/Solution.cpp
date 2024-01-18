class Solution {
public:
    bool isConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int n = nums.size();
        return s.size() == n && mx == mi + n - 1;
    }
};