class Solution {
public:
    void duplicateZeros(vector<int>& arr) {
        int n = arr.size();
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (~j) {
            arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }
};