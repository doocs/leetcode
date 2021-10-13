class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = 1;
        int res = 0, s = 0;
        for (int num : nums)
        {
            s += num;
            res += mp[s - k];
            ++mp[s];
        }
        return res;
    }
};