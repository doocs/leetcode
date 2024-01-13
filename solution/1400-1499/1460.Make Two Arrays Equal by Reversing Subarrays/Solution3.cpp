class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt(1001);
        for (int& v : target) ++cnt[v];
        for (int& v : arr)
            if (--cnt[v] < 0) return false;
        return true;
    }
};