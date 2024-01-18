class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        sort(arr.begin(), arr.end(), [&](auto& a, auto& b) -> bool {
            int x = __builtin_popcount(a), y = __builtin_popcount(b);
            return x < y || (x == y && a < b);
        });
        return arr;
    }
};