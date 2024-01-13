class Solution {
public:
    vector<int> sortArray(vector<int>& nums) {
        function<void(int, int)> merge_sort = [&](int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) >> 1;
            merge_sort(l, mid);
            merge_sort(mid + 1, r);
            int i = l, j = mid + 1, k = 0;
            int tmp[r - l + 1];
            while (i <= mid && j <= r) {
                if (nums[i] <= nums[j]) {
                    tmp[k++] = nums[i++];
                } else {
                    tmp[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                tmp[k++] = nums[i++];
            }
            while (j <= r) {
                tmp[k++] = nums[j++];
            }
            for (i = l; i <= r; ++i) {
                nums[i] = tmp[i - l];
            }
        };
        merge_sort(0, nums.size() - 1);
        return nums;
    }
};