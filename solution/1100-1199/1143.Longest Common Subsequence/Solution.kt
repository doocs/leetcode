class Solution {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val m = text1.length
        val n = text2.length
        val f = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            for (j in 1..n) {
                if (text1[i - 1] == text2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1])
                }
            }
        }
        return f[m][n]
    }
}
