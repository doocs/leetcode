object Solution {
    def isBalanced(num: String): Boolean = {
        val f = Array(0, 0)
        for (i <- num.indices) {
            f(i & 1) += num(i) - '0'
        }
        f(0) == f(1)
    }
}
