class Solution {
    fun possibleToStamp(grid: Array<IntArray>, stampHeight: Int, stampWidth: Int): Boolean {
        val m = grid.size
        val n = grid[0].size

        var prefix_sums_matrix = Array(m + 1) { IntArray(n + 1) }
        var diff_matrix = Array(m + 1) { IntArray(n + 1) }
        var sum_matrix = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..<m) {
            for (j in 0..<n) {
                prefix_sums_matrix[i + 1][j + 1] =
                    prefix_sums_matrix[i + 1][j] +
                    prefix_sums_matrix[i][j + 1] -
                    prefix_sums_matrix[i][j] +
                    grid[i][j]
            }
        }

        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] != 0) {
                    continue
                }

                val bottom = i + stampHeight
                val right = j + stampWidth

                if (bottom > m || right > n) {
                    continue
                }

                val sum = prefix_sums_matrix[bottom][right] -
                    prefix_sums_matrix[bottom][j] -
                    prefix_sums_matrix[i][right] +
                    prefix_sums_matrix[i][j]

                if (sum == 0) {
                    diff_matrix[i][j] += 1
                    diff_matrix[bottom][right] += 1

                    diff_matrix[i][right] -= 1
                    diff_matrix[bottom][j] -= 1
                }
            }
        }

        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] != 0) {
                    continue
                }

                val sum = sum_matrix[i][j + 1] +
                    sum_matrix[i + 1][j] -
                    sum_matrix[i][j] +
                    diff_matrix[i][j]

                if (sum == 0) {
                    return false
                }

                sum_matrix[i + 1][j + 1] = sum
            }
        }

        return true
    }
}
