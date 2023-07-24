class Solution {
public:
    bool isGood(vector<int>& nums) {
        int n = nums.size() - 1;
        vector<int> cnt(201);
        for (int x : nums) {
            ++cnt[x];
        }
        cnt[n] -= 2;
        for (int i = 1; i < n; ++i) {
            --cnt[i];
        }
        for (int x : cnt) {
            if (x) {
                return false;
            }
        }
        return true;
    }
};