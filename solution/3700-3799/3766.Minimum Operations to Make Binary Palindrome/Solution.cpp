vector<int> primes;

auto init = [] {
    int N = 1 << 14;
    for (int i = 0; i < N; ++i) {
        string s = bitset<14>(i).to_string();
        s = s.substr(s.find_first_not_of('0') == string::npos ? 13 : s.find_first_not_of('0'));
        string rs = s;
        reverse(rs.begin(), rs.end());
        if (s == rs) {
            primes.push_back(i);
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
            int i = lower_bound(primes.begin(), primes.end(), x) - primes.begin();
            if (i < (int) primes.size()) {
                ans[k] = min(ans[k], primes[i] - x);
            }
            if (i >= 1) {
                ans[k] = min(ans[k], x - primes[i - 1]);
            }
        }
        return ans;
    }
};
