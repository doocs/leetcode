class Solution {
public:
    int f(int x) {
        int s = 0;
        while (x) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {f(nums[i]), nums[i]};
        sort(arr.begin(), arr.end());
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) d[arr[i].second] = i;
        vector<char> vis(n, 0);
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                --ans;
                int j = i;
                while (!vis[j]) {
                    vis[j] = 1;
                    j = d[nums[j]];
                }
            }
        }
        return ans;
    }
};
