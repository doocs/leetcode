class Solution {
public:
    int makeArrayIncreasing(vector<int>& arr1, vector<int>& arr2) {
        sort(arr2.begin(), arr2.end());
        arr2.erase(unique(arr2.begin(), arr2.end()), arr2.end());
        const int inf = 1 << 30;
        arr1.insert(arr1.begin(), -inf);
        arr1.push_back(inf);
        int n = arr1.size();
        vector<int> f(n, inf);
        f[0] = 0;
        for (int i = 1; i < n; ++i) {
            if (arr1[i - 1] < arr1[i]) {
                f[i] = f[i - 1];
            }
            int j = lower_bound(arr2.begin(), arr2.end(), arr1[i]) - arr2.begin();
            for (int k = 1; k <= min(i - 1, j); ++k) {
                if (arr1[i - k - 1] < arr2[j - k]) {
                    f[i] = min(f[i], f[i - k - 1] + k);
                }
            }
        }
        return f[n - 1] >= inf ? -1 : f[n - 1];
    }
};