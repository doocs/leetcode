class Solution {
public:
    int countTriplets(vector<int>& arr) {
        int ans = 0, n = arr.size();
        for (int i = 0; i < n; ++i) {
            int s = arr[i];
            for (int k = i + 1; k < n; ++k) {
                s ^= arr[k];
                if (s == 0) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }
};
