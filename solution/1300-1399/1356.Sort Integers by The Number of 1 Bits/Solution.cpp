class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        for (int& x : arr) {
            x += __builtin_popcount(x) * 100000;
        }
        ranges::sort(arr);
        for (int& x : arr) {
            x %= 100000;
        }
        return arr;
    }
};
