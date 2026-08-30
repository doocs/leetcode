class Solution {
public:
    vector<string> largestString(vector<int>& nums) {
        vector<string> ans;
        ans.reserve(nums.size());
        for (int x : nums) {
            string s;
            for (int j = 25; j >= 0; --j) {
                for (int t = x >> j; t > 0; --t) {
                    s.push_back('a' + j);
                }
                x &= (1 << j) - 1;
            }
            ans.push_back(s);
        }
        return ans;
    }
};
