class NumArray(nums: IntArray) {
    private val prefix_sums: IntArray

    init {
        val nums_size = nums.size
        this.prefix_sums = IntArray(nums_size + 1)
        for (i in 0..<nums_size) {
            this.prefix_sums[i + 1] = this.prefix_sums[i] + nums[i]
        }
    }

    fun sumRange(left: Int, right: Int): Int = this.prefix_sums[right + 1] - this.prefix_sums[left]
}

/**
 * Your NumArray object will be instantiated and called as such: var obj = NumArray(nums) var
 * param_1 = obj.sumRange(left,right)
 */
