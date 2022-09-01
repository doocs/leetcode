class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& arr) {
        int s = 0;
        for (int v : arr) s += v;
        if (s % 3) return false;
        int i = 0, j = arr.size() - 1;
        int a = 0, b = 0;
        while (i < arr.size()) {
            a += arr[i];
            if (a == s / 3) {
                break;
            }
            ++i;
        }
        while (~j) {
            b += arr[j];
            if (b == s / 3) {
                break;
            }
            --j;
        }
        return i < j - 1;
    }
};