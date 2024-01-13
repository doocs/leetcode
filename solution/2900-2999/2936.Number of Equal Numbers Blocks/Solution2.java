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
        return f(nums, 0, nums.size() - 1);
    }

    private int f(BigArray nums, long l, long r) {
        if (nums.at(l) == nums.at(r)) {
            return 1;
        }
        long mid = (l + r) >> 1;
        int a = f(nums, l, mid);
        int b = f(nums, mid + 1, r);
        return a + b - (nums.at(mid) == nums.at(mid + 1) ? 1 : 0);
    }
}