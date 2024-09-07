class NumMatrix(matrix: Array<IntArray>) {
    private val n: Int
    private val m: Int
    private val matrix: Array<IntArray>
    private val prefix_sums_matrix: Array<IntArray>
    private var initialized: Boolean

    init {
        this.n = matrix.size
        this.m = matrix[0].size
        this.matrix = matrix
        this.prefix_sums_matrix = Array(n + 1) { IntArray(m + 1) }
        this.initialized = false
    }

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        this.init()
        return this.prefix_sums_matrix[row2 + 1][col2 + 1] -
            this.prefix_sums_matrix[row2 + 1][col1] -
            this.prefix_sums_matrix[row1][col2 + 1] +
            this.prefix_sums_matrix[row1][col1]
    }

    private fun init(): Boolean {
        if (!this.initialized) {
            for (i in 0..<this.n) {
                for (j in 0..<this.m) {
                    this.prefix_sums_matrix[i + 1][j + 1] =
                        this.prefix_sums_matrix[i + 1][j] +
                        this.prefix_sums_matrix[i][j + 1] -
                        this.prefix_sums_matrix[i][j] +
                        this.matrix[i][j]
                }
            }
            this.initialized = true
            return true
        }
        return false
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such: var obj = NumMatrix(matrix) var
 * param_1 = obj.sumRegion(row1,col1,row2,col2)
 */
