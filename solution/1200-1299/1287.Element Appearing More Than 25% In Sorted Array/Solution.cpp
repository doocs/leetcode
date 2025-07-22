class Solution {
public:
    int findSpecialInteger(vector<int>& arr) {
        for (int i = 0;; ++i) {
            if (arr[i] == (arr[i + (arr.size() >> 2)])) {
                return arr[i];
            }
        }
    }
};
