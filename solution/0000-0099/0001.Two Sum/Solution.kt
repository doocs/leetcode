class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val m = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, x ->
            val y = target - x
            val j = m.get(y)
            if (j != null) {
                return intArrayOf(j, i)
            }
            m[x] = i
        }
        return intArrayOf()
    }
}
