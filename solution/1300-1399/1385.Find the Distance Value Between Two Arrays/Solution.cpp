class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        auto check = [&](int a) -> bool {
            auto it = lower_bound(arr2.begin(), arr2.end(), a - d);
            return it == arr2.end() || *it > a + d;
        };
        sort(arr2.begin(), arr2.end());
        int ans = 0;
        for (int& a : arr1) {
            ans += check(a);
        }
        return ans;
    }
};