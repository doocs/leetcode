class Solution {
public:
    vector<bool> checkArithmeticSubarrays(vector<int>& nums, vector<int>& l, vector<int>& r) {
        vector<bool> ans;
        auto check = [](vector<int>& nums, int l, int r) {
            unordered_set<int> s;
            int n = r - l + 1;
            int a1 = 1 << 30, an = -a1;
            for (int i = l; i <= r; ++i) {
                s.insert(nums[i]);
                a1 = min(a1, nums[i]);
                an = max(an, nums[i]);
            }
            if ((an - a1) % (n - 1)) {
                return false;
            }
            int d = (an - a1) / (n - 1);
            for (int i = 1; i < n; ++i) {
                if (!s.count(a1 + (i - 1) * d)) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0; i < l.size(); ++i) {
            ans.push_back(check(nums, l[i], r[i]));
        }
        return ans;
    }
};