class Solution {
public:
    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();
        vector<int> prefMax(n + 1);
        for (int i = 0; i < n; ++i) {
            prefMax[i + 1] = max(prefMax[i], nums[i]);
        }
        vector<int> suffMin(n + 1, 1000000000);
        for (int i = n - 1; i >= 0; --i) {
            suffMin[i] = min(nums[i], suffMin[i + 1]);
        }
        vector<int> prefDrop(n);
        for (int i = 1; i < n; ++i) {
            prefDrop[i] = prefDrop[i - 1] + (nums[i - 1] > nums[i]);
        }
        auto check = [&](int k) {
            for (int start = 0; start < n; start += k) {
                int end = start + k - 1;
                int drops = prefDrop[end] - prefDrop[start];
                if (nums[end] > nums[start]) {
                    ++drops;
                }
                if (drops > 1 || prefMax[start] > suffMin[start]) {
                    return false;
                }
            }
            return true;
        };
        int ans = 0;
        for (int i = 1; i <= n / i; ++i) {
            if (n % i == 0) {
                if (check(i)) {
                    ans += i;
                }
                if (i != n / i && check(n / i)) {
                    ans += n / i;
                }
            }
        }
        return ans;
    }
};
