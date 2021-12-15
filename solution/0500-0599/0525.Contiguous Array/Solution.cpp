class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        int presum = 0;
        int maxlen = 0;
        unordered_map<int, int> mp;
        mp[0] = -1;
        for (int i = 0; i < nums.size(); i++) {
            presum += nums[i] == 0? -1: 1;
            if (mp.find(presum) != mp.end())
                maxlen = max(maxlen, i - mp[presum]);
            else
                mp[presum] = i;
        }

        return maxlen;
    }
};