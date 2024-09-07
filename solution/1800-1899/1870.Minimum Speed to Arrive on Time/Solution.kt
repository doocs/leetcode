class Solution {
    fun minSpeedOnTime(dist: IntArray, hour: Double): Int {
        val n = dist.size
        if (n > Math.ceil(hour)) {
            return -1
        }
        val m = 1e7.toInt()
        var left = 1
        var right = m + 1
        while (left < right) {
            val middle = (left + right) / 2
            var time = 0.0
            dist.forEachIndexed { i, item ->
                val t = item.toDouble() / middle
                time += if (i == n - 1) t else Math.ceil(t)
            }
            if (time > hour) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return if (left > m) -1 else left
    }
}
