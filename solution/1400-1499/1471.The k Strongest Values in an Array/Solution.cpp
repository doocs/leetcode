class Solution {
public:
    vector<int> getStrongest(vector<int>& arr, int k) {
        sort(arr.begin(), arr.end());
        int m = arr[(arr.size() - 1) >> 1];
        sort(arr.begin(), arr.end(), [&](int a, int b) {
            int x = abs(a - m), y = abs(b - m);
            return x == y ? a > b : x > y;
        });
        vector<int> ans(arr.begin(), arr.begin() + k);
        return ans;
    }
};