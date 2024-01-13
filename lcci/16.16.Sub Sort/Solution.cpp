class Solution {
public:
    vector<int> subSort(vector<int>& array) {
        int n = array.size();
        int mi = INT_MAX, mx = INT_MIN;
        int left = -1, right = -1;
        for (int i = 0; i < n; ++i) {
            if (array[i] < mx) {
                right = i;
            } else {
                mx = array[i];
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (array[i] > mi) {
                left = i;
            } else {
                mi = array[i];
            }
        }
        return {left, right};
    }
};