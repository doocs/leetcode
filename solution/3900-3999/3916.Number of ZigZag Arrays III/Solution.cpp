class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        int cnt[2]{};
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] & 1; // x 的奇偶性
            ans[i] = cnt[x ^ 1]; // 查询右侧奇偶性不等于 x（即 x^1）的元素个数 
            cnt[x]++;
        }
        return ans;
    }
};