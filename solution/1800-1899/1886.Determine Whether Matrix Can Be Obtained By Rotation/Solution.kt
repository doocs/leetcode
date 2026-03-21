class Solution {
    fun findRotation(mat: Array<IntArray>, target: Array<IntArray>): Boolean {
        val n = mat.size
        var ok = 0b1111
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (mat[i][j] != target[i][j]) {
                    ok = ok and 0b1110
                }
                if (mat[j][n - 1 - i] != target[i][j]) {
                    ok = ok and 0b1101
                }
                if (mat[n - 1 - i][n - 1 - j] != target[i][j]) {
                    ok = ok and 0b1011
                }
                if (mat[n - 1 - j][i] != target[i][j]) {
                    ok = ok and 0b0111
                }
                if (ok == 0) {
                    return false
                }
            }
        }
        return ok != 0
    }
}
