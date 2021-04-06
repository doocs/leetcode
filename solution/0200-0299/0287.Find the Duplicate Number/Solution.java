class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            int cnt = 0;
            for (int e : nums) {
                if (e <= mid) ++cnt;
            }
            if (cnt <= mid) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}