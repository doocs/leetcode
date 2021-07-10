func rob(nums []int) int {
    a, b, n := 0, nums[0], len(nums)
    for i := 1; i < n; i++ {
        a, b = b, max(nums[i] + a, b)
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}