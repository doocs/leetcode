class Solution {
public:
    int minMoves(vector<int>& nums, int k) {
        vector<int> arr;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i]) {
                arr.push_back(i);
            }
        }
        int m = arr.size();
        long s[m + 1];
        s[0] = 1;
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + arr[i];
        }
        long ans = 1L << 60;
        int x = (k + 1) / 2;
        int y = k - x;
        for (int i = x - 1; i < m - y; ++i) {
            int j = arr[i];
            int ls = s[i + 1] - s[i + 1 - x];
            int rs = s[i + 1 + y] - s[i + 1];
            long a = (j + j - x + 1L) * x / 2 - ls;
            long b = rs - (j + 1L + j + y) * y / 2;
            ans = min(ans, a + b);
        }
        return ans;
    }
};