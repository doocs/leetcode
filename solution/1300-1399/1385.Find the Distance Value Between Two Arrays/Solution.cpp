class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        int res = 0;
        for (auto& a : arr1) {
            bool exist = false;
            for (auto& b : arr2) {
                if (abs(a - b) <= d) {
                    exist = true;
                    break;
                }
            }
            if (!exist) ++res;
        }
        return res;
    }
};