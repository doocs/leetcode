class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = -1;
        int s = 0, ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            if (mp.count(s - k)) ans = max(ans, i - mp[s - k]);
            if (!mp.count(s)) mp[s] = i;
        }
        return ans;
    }
};