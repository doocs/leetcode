class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var left = 0
        var count = 0
        var product = 1
        nums.forEachIndexed { right, num ->
            product *= num
            while (product >= k && left <= right) {
                product /= nums[left++]
            }
            count += right - left + 1
        }
        return count
    }
}
