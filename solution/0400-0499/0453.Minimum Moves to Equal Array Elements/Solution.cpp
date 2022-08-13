class Solution {
public:
    int minMoves(vector<int>& nums) {
        int s = 0;
        int mi = INT_MAX;
        for (int num : nums) {
            s += num;
            mi = min(mi, num);
        }
        return s - mi * nums.size();
    }
};