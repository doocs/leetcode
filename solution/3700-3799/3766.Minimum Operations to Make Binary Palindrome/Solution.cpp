vector<int> p;

auto init = [] {
    int N = 1 << 14;
    for (int i = 0; i < N; ++i) {
        string s = bitset<14>(i).to_string();
        s = s.substr(s.find_first_not_of('0') == string::npos ? 13 : s.find_first_not_of('0'));
        string rs = s;
        reverse(rs.begin(), rs.end());
        if (s == rs) {
            p.push_back(i);
        }
    }
    return 0;
}();

class Solution {
public:
    vector<int> minOperations(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, INT_MAX);
        for (int k = 0; k < n; ++k) {
            int x = nums[k];
            int i = lower_bound(p.begin(), p.end(), x) - p.begin();
            if (i < (int) p.size()) {
                ans[k] = min(ans[k], p[i] - x);
            }
            if (i >= 1) {
                ans[k] = min(ans[k], x - p[i - 1]);
            }
        }
        return ans;
    }
};
