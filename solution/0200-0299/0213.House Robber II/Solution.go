func rob(nums []int) int {
    n := len(nums)
    if n == 1 {
        return nums[0]
    }
    s1, s2 := robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
    return max(s1, s2)
}

func robRange(nums[]int, start int, end int) int {
    if end - start == 0 {
        return nums[start]
    }
    pre, cur := 0, nums[start]
    for i := start + 1; i < end + 1; i++ {
        pre, cur = cur, max(pre + nums[i], cur)
    }
    return cur
}

func max(a, b int) int {
    if (a > b) {
        return a
    }
    return b
}