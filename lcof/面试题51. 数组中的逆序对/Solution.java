class Solution {
    private int res = 0;
    public int reversePairs(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        mergeSort(nums, 0, n - 1);
        return res;
    }

    private void mergeSort(int[] nums, int s, int e) {
        if (s == e) {
            return;
        }
        int mid = (s + e) >>> 1;
        mergeSort(nums, s, mid);
        mergeSort(nums, mid + 1, e);
        merge(nums, s, mid, e);
    }

    private void merge(int[] nums, int s, int mid, int e) {
        int n = e - s + 1;
        int[] help = new int[n];
        int i = s, j = mid + 1, idx = 0;
        while (i <= mid && j <= e) {
            if (nums[i] > nums[j]) {
                res += (mid - i + 1);
                help[idx++] = nums[j++];
            } else {
                help[idx++] = nums[i++];
            }
        }
        while (i <= mid) {
            help[idx++] = nums[i++];
        }
        while (j <= e) {
            help[idx++] = nums[j++];
        }
        for (int t = 0; t < n; ++t) {
            nums[s + t] = help[t];
        }
    }
}