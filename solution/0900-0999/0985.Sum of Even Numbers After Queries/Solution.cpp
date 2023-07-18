class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int s = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                s += x;
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int v = q[0], i = q[1];
            if (nums[i] % 2 == 0) {
                s -= nums[i];
            }
            nums[i] += v;
            if (nums[i] % 2 == 0) {
                s += nums[i];
            }
            ans.push_back(s);
        }
        return ans;
    }
};