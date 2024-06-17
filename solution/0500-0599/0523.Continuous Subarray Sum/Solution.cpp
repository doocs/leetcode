class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> d{{0, -1}};
        int s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s = (s + nums[i]) % k;
            if (!d.contains(s)) {
                d[s] = i;
            } else if (i - d[s] > 1) {
                return true;
            }
        }
        return false;
    }
};