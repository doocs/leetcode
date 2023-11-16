/**
 * Definition for BigArray.
 * class BigArray {
 *     public BigArray(int[] elements);
 *     public int at(long index);
 *     public long size();
 * }
 */
class Solution {
    public int countBlocks(BigArray nums) {
        int ans = 0;
        for (long i = 0, n = nums.size(); i < n; ++ans) {
            i = search(nums, i, n);
        }
        return ans;
    }

    private long search(BigArray nums, long l, long n) {
        long r = n;
        int x = nums.at(l);
        while (l < r) {
            long mid = (l + r) >> 1;
            if (nums.at(mid) != x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}