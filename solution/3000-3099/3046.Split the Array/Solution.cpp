class Solution {
public:
    bool isPossibleToSplit(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
};