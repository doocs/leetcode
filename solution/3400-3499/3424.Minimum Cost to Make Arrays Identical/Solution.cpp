class Solution {
public:
    long long minCost(vector<int>& arr, vector<int>& brr, long long k) {
        auto calc = [&](vector<int>& arr, vector<int>& brr) {
            long long ans = 0;
            for (int i = 0; i < arr.size(); ++i) {
                ans += abs(arr[i] - brr[i]);
            }
            return ans;
        };
        long long c1 = calc(arr, brr);
        ranges::sort(arr);
        ranges::sort(brr);
        long long c2 = calc(arr, brr) + k;
        return min(c1, c2);
    }
};
