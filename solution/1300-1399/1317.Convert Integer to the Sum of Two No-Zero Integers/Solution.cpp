class Solution {
public:
    vector<int> getNoZeroIntegers(int n) {
        for (int a = 1; a < n; ++a) {
            int b = n - a;
            if ((to_string(a) + to_string(b)).find('0') == string::npos) return {a, b};
        }
        return {};
    }
};