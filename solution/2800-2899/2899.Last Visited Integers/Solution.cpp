class Solution {
public:
    vector<int> lastVisitedIntegers(vector<string>& words) {
        vector<int> nums;
        vector<int> ans;
        int k = 0;
        for (auto& w : words) {
            if (w == "prev") {
                ++k;
                int i = nums.size() - k;
                ans.push_back(i < 0 ? -1 : nums[i]);
            } else {
                k = 0;
                nums.push_back(stoi(w));
            }
        }
        return ans;
    }
};