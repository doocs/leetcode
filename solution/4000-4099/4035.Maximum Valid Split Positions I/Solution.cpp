class Solution {
public:
    int maxValidSplits(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int del = -1; del < n; ++del) {
            vector<int> arr;
            arr.reserve(n);
            for (int i = 0; i < n; ++i) {
                if (i != del) {
                    arr.push_back(nums[i]);
                }
            }
            ans = max(ans, calc(arr));
        }
        return ans;
    }

private:
    int calc(const vector<int>& arr) {
        int m = arr.size();
        vector<int> pre(m), suf(m);
        pre[0] = arr[0];
        for (int i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }
};
