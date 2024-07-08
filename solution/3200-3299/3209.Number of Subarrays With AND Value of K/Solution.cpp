class Solution {
public:
    long long countSubarrays(vector<int>& nums, int k) {
        long long ans = 0;
        unordered_map<int, int> pre;
        for (int x : nums) {
            unordered_map<int, int> cur;
            for (auto& [y, v] : pre) {
                cur[x & y] += v;
            }
            cur[x]++;
            ans += cur[k];
            pre = cur;
        }
        return ans;
    }
};