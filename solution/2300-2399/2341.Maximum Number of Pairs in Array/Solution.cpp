class Solution {
public:
    vector<int> numberOfPairs(vector<int>& nums) {
        vector<int> cnt(101);
        for (int& x : nums) {
            ++cnt[x];
        }
        int s = 0;
        for (int& v : cnt) {
            s += v >> 1;
        }
        return {s, (int) nums.size() - s * 2};
    }
};