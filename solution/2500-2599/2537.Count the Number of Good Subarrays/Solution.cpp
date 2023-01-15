class Solution {
public:
    long long countGood(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        long long cur = 0;
        int i = 0;
        for (int& x : nums) {
            cur += cnt[x]++;
            while (cur - cnt[nums[i]] + 1 >= k) {
                cur -= --cnt[nums[i++]];
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
};
