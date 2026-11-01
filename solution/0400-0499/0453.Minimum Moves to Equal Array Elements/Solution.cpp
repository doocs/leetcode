class Solution {
public:
    int minMoves(vector<int>& nums) {
        long long s = 0;
        int mi = nums[0];
        int n = nums.size();
        for (int x : nums) {
            s += x;
            mi = min(mi, x);
        }
        return (int) (s - 1LL * mi * n);
    }
};