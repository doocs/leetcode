class Solution {
public:
    vector<int> maxNumber(vector<int>& nums1, vector<int>& nums2, int k) {
        auto f = [](vector<int>& nums, int k) {
            int n = nums.size();
            vector<int> stk(k);
            int top = -1;
            int remain = n - k;
            for (int x : nums) {
                while (top >= 0 && stk[top] < x && remain > 0) {
                    --top;
                    --remain;
                }
                if (top + 1 < k) {
                    stk[++top] = x;
                } else {
                    --remain;
                }
            }
            return stk;
        };
        function<bool(vector<int>&, vector<int>&, int, int)> compare = [&](vector<int>& nums1, vector<int>& nums2, int i, int j) -> bool {
            if (i >= nums1.size()) {
                return false;
            }
            if (j >= nums2.size()) {
                return true;
            }
            if (nums1[i] > nums2[j]) {
                return true;
            }
            if (nums1[i] < nums2[j]) {
                return false;
            }
            return compare(nums1, nums2, i + 1, j + 1);
        };

        auto merge = [&](vector<int>& nums1, vector<int>& nums2) {
            int m = nums1.size(), n = nums2.size();
            int i = 0, j = 0;
            vector<int> ans(m + n);
            for (int k = 0; k < m + n; ++k) {
                if (compare(nums1, nums2, i, j)) {
                    ans[k] = nums1[i++];
                } else {
                    ans[k] = nums2[j++];
                }
            }
            return ans;
        };

        int m = nums1.size(), n = nums2.size();
        int l = max(0, k - n), r = min(k, m);
        vector<int> ans(k);
        for (int x = l; x <= r; ++x) {
            vector<int> arr1 = f(nums1, x);
            vector<int> arr2 = f(nums2, k - x);
            vector<int> arr = merge(arr1, arr2);
            if (ans < arr) {
                ans = move(arr);
            }
        }
        return ans;
    }
};