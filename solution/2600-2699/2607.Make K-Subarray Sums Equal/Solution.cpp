class Solution {
public:
    long long makeSubKSumEqual(vector<int>& arr, int k) {
        int n = arr.size();
        int g = gcd(n, k);
        long long ans = 0;
        for (int i = 0; i < g; ++i) {
            vector<int> t;
            for (int j = i; j < n; j += g) {
                t.push_back(arr[j]);
            }
            sort(t.begin(), t.end());
            int mid = t[t.size() / 2];
            for (int x : t) {
                ans += abs(x - mid);
            }
        }
        return ans;
    }
};