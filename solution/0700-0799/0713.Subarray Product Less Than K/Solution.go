func numSubarrayProductLessThanK(nums []int, k int) (ans int) {
    l, p := 0, 1
    for r, x := range nums {
        p *= x
        for l <= r && p >= k {
            p /= nums[l]
            l++
        }
        ans += r - l + 1
    }
    return
}
