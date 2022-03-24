class Solution {
public:
    bool divideArray(vector<int>& nums) {
        vector<int> cnt(510);
        for (int& v : nums) ++cnt[v];
        for (int& v : cnt)
            if (v % 2)
                return false;
        return true;
    }
};