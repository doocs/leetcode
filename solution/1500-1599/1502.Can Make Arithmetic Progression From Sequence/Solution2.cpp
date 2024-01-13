class Solution {
public:
    bool canMakeArithmeticProgression(vector<int>& arr) {
        auto [a, b] = minmax_element(arr.begin(), arr.end());
        int n = arr.size();
        if ((*b - *a) % (n - 1) != 0) {
            return false;
        }
        int d = (*b - *a) / (n - 1);
        unordered_set<int> s(arr.begin(), arr.end());
        for (int i = 0; i < n; ++i) {
            if (!s.count(*a + d * i)) {
                return false;
            }
        }
        return true;
    }
};