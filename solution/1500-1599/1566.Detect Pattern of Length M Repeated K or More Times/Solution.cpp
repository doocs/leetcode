class Solution {
public:
    bool containsPattern(vector<int>& arr, int m, int k) {
        if (arr.size() < m * k) {
            return false;
        }
        int cnt = 0, target = (k - 1) * m;
        for (int i = m; i < arr.size(); ++i) {
            if (arr[i] == arr[i - m]) {
                if (++cnt == target) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
};
