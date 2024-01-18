class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        make_heap(nums.begin(), nums.end());
        long long ans = 0;
        while (k--) {
            int v = nums[0];
            ans += v;
            pop_heap(nums.begin(), nums.end());
            nums.back() = (v + 2) / 3;
            push_heap(nums.begin(), nums.end());
        }
        return ans;
    }
};