func searchInsert(nums []int, target int) int {
    l, h := 0, len(nums) - 1
    for l <= h {
        m := l + ((h - l) >> 1)
        if nums[m] == target {
            return m
        }
        if nums[m] < target {
            l = m + 1
        } else {
            h = m - 1
        }
    }
    return l
}