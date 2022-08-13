class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        int ans = 0;
        for (int& a : arr1)
            ans += check(arr2, a, d);
        return ans;
    }

    bool check(vector<int>& arr, int a, int d) {
        for (int& b : arr)
            if (abs(a - b) <= d)
                return false;
        return true;
    }
};