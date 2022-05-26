class Solution {
public:
    string minNumber(vector<int>& nums) {
        int n = nums.size();
        vector<string> strs(n);
        for (int i = 0; i < n; ++i) {
            strs[i] = to_string(nums[i]);
        }
        sort(strs.begin(), strs.end(), [](const string& s1, const string& s2) {
            return s1 + s2 < s2 + s1;
        });
        string ans;
        for (int i = 0; i < n; ++i) {
            ans += strs[i];
        }
        return ans;
    }
};
