class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var ans = 0
        var l = 0
        var p = 1

        for (r in nums.indices) {
            p *= nums[r]
            while (l <= r && p >= k) {
                p /= nums[l]
                l++
            }
            ans += r - l + 1
        }

        return ans
    }
}
