class Solution {
public:
    int sortArray(vector<int>& nums) {
        int n = nums.size();
        auto f = [&](vector<int>& nums, int k) {
            vector<bool> vis(n);
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (i == nums[i] || vis[i]) continue;
                int j = i;
                ++cnt;
                while (!vis[j]) {
                    vis[j] = true;
                    ++cnt;
                    j = nums[j];
                }
            }
            if (nums[k] != k) cnt -= 2;
            return cnt;
        };

        int a = f(nums, 0);
        vector<int> arr = nums;
        for (int& v : arr) v = (v - 1 + n) % n;
        int b = f(arr, n - 1);
        return min(a, b);
    }
};