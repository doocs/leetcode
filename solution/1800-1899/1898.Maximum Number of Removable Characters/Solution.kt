class Solution {
    fun maximumRemovals(s: String, p: String, removable: IntArray): Int {
        val m = s.length
        val n = p.length
        var l = 0
        var r = removable.size

        fun check(k: Int): Boolean {
            val rem = BooleanArray(m)
            for (i in 0 until k) {
                rem[removable[i]] = true
            }
            var i = 0
            var j = 0
            while (i < m && j < n) {
                if (!rem[i] && s[i] == p[j]) {
                    j++
                }
                i++
            }
            return j == n
        }

        while (l < r) {
            val mid = (l + r + 1) / 2
            if (check(mid)) {
                l = mid
            } else {
                r = mid - 1
            }
        }

        return l
    }
}
