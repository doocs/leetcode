class Solution {
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        val n = cardPoints.size
        var s = cardPoints.sliceArray(n - k until n).sum()
        var ans = s
        for (i in 0 until k) {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = maxOf(ans, s)
        }
        return ans
    }
}
