class Solution {
public:
    vector<int> getNoZeroIntegers(int n) {
        for (int a = 1;; ++a) {
            int b = n - a;
            if ((to_string(a) + to_string(b)).find('0') == -1) {
                return {a, b};
            }
        }
    }
};