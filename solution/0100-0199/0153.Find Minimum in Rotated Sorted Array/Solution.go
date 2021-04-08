func findMin(nums []int) int {
    l, r := 0, len(nums) - 1
    if nums[l] < nums[r] {
        return nums[0]
    }
    for l < r {
        m := (l + r) >> 1
        if nums[m] > nums[r] {
            l = m + 1
        } else {
            r = m
        }
    }
    return nums[l]
}