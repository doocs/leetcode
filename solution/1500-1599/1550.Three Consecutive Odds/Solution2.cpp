class Solution {
public:
    bool threeConsecutiveOdds(vector<int>& arr) {
        for (int i = 2, n = arr.size(); i < n; ++i) {
            if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
                return true;
            }
        }
        return false;
    }
};
