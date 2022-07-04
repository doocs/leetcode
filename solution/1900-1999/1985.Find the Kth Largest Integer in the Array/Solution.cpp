class Solution {
public:
    string kthLargestNumber(vector<string>& nums, int k) {
        auto cmp = [](const string& a, const string& b) { return a.size() == b.size() ? a > b : a.size() > b.size(); };
        sort(nums.begin(), nums.end(), cmp);
        return nums[k - 1];
    }
};