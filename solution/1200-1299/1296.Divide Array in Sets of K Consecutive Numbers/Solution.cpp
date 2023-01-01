class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int& v : nums) ++cnt[v];
        sort(nums.begin(), nums.end());
        for (int& v : nums) {
            if (cnt.count(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.count(x)) {
                        return false;
                    }
                    if (--cnt[x] == 0) {
                        cnt.erase(x);
                    }
                }
            }
        }
        return true;
    }
};