object Solution {
    def maxScore(cardPoints: Array[Int], k: Int): Int = {
        val n = cardPoints.length
        var s = cardPoints.takeRight(k).sum
        var ans = s
        for (i <- 0 until k) {
            s += cardPoints(i) - cardPoints(n - k + i)
            ans = ans.max(s)
        }
        ans
    }
}
