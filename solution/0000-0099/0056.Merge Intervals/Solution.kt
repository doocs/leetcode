class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        val n = intervals.size
        var i = 0
        while (i < n) {
            val left = intervals[i][0]
            var right = intervals[i][1]
            while (true) {
                i++
                if (i < n && right >= intervals[i][0]) {
                    right = maxOf(right, intervals[i][1])
                } else {
                    result.add(intArrayOf(left, right))
                    break
                }
            }
        }
        return result.toTypedArray()
    }
}
