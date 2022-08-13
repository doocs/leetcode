class Solution {
public:
    vector<int> nums;
    int k;
    vector<long long> presum;
    int n;

    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        n = nums.size();
        this->k = k;
        this->nums = nums;
        presum = vector<long long>(n + 1);
        for (int i = 1; i <= n; ++i) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int left = 1, right = n;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    bool check(int count) {
        for (int i = 0; i < n - count + 1; ++i) {
            int j = i + count - 1;
            if ((long long)nums[j] * count - (presum[j + 1] - presum[i]) <= k) return true;
        }
        return false;
    }
};