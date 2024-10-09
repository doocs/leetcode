class Solution {
public:
    vector<int> gcdValues(vector<int>& nums, vector<long long>& queries) {
        int mx = ranges::max(nums);
        vector<int> cnt(mx + 1);
        vector<long long> cntG(mx + 1);
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = mx; i; --i) {
            long long v = 0;
            for (int j = i; j <= mx; j += i) {
                v += cnt[j];
                cntG[i] -= cntG[j];
            }
            cntG[i] += 1LL * v * (v - 1) / 2;
        }
        for (int i = 2; i <= mx; ++i) {
            cntG[i] += cntG[i - 1];
        }
        vector<int> ans;
        for (auto&& q : queries) {
            ans.push_back(upper_bound(cntG.begin(), cntG.end(), q) - cntG.begin());
        }
        return ans;
    }
};
