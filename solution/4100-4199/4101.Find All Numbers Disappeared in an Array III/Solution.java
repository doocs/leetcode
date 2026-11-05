class Solution {
    public int[][] findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int l = lowerBound(nums, lower);
        int r = lowerBound(nums, upper + 1);
        List<int[]> ans = new ArrayList<>();
        int pre = lower;
        for (int i = l; i < r; i++) {
            int cur = nums[i];
            if (pre < cur) {
                ans.add(new int[] {pre, cur - 1});
            }
            pre = cur + 1;
        }
        if (pre <= upper) {
            ans.add(new int[] {pre, upper});
        }
        return ans.toArray(new int[ans.size()][]);
    }

    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}