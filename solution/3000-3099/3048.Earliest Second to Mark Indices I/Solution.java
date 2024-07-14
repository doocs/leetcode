class Solution {
    private int[] nums;
    private int[] changeIndices;

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        this.nums = nums;
        this.changeIndices = changeIndices;
        int m = changeIndices.length;
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int t) {
        int[] last = new int[nums.length + 1];
        for (int s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        int decrement = 0;
        int marked = 0;
        for (int s = 0; s < t; ++s) {
            int i = changeIndices[s];
            if (last[i] == s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked == nums.length;
    }
}