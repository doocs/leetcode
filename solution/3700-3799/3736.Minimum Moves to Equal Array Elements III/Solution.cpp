class Solution {
public:
    int minMoves(vector<int>& nums) {
        int n = nums.size();
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
};
