class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        bool s[n + 1];
        memset(s, false, sizeof(s));
        for (int& x : nums) {
            s[x] = true;
        }
        vector<int> ans;
        for (int i = 1; i <= n; ++i) {
            if (!s[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};