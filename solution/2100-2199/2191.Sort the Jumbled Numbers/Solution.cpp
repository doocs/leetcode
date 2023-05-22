class Solution {
public:
    vector<int> sortJumbled(vector<int>& mapping, vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            int y = x == 0 ? mapping[0] : 0;
            int k = 1;
            for (; x; x /= 10) {
                y += k * mapping[x % 10];
                k *= 10;
            }
            arr[i] = {y, i};
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& [_, i] : arr) {
            ans.push_back(nums[i]);
        }
        return ans;
    }
};