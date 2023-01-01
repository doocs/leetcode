class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        for (int& v : arr) {
            v += __builtin_popcount(v) * 100000;
        }
        sort(arr.begin(), arr.end());
        for (int& v : arr) {
            v %= 100000;
        }
        return arr;
    }
};