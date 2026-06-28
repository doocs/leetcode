func maxSum(nums []int, k int, mul int) int64 {
    sort.Ints(nums)
    n := len(nums)
    var ans int64 = 0

    for i := n - 1; i >= n-k; i-- {
        m := max(1, mul)
        ans += int64(nums[i]) * int64(m)
        mul--
    }

    return ans
}