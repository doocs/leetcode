class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int ans = arr[0], f = arr[0], g = 0;
        for (int i = 1; i < arr.size(); ++i) {
            int ff = g + arr[i] * (i / 2 + 1);
            int gg = f + arr[i] * ((i + 1) / 2);
            f = ff;
            g = gg;
            ans += f;
        }
        return ans;
    }
};
