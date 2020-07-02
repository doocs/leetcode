func findRepeatNumber(nums []int) int {
    for i := 0; i < len(nums); i++ {
        for nums[i] != i {
            if nums[i] == nums[nums[i]] {
                return nums[i]
            }
            nums[i], nums[nums[i]] = nums[nums[i]], nums[i]
        }
    }
    return -1
}