class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> f(rowIndex + 1, 1);
        for (int i = 2; i < rowIndex + 1; ++i) {
            for (int j = i - 1; j; --j) {
                f[j] += f[j - 1];
            }
        }
        return f;
    }
};