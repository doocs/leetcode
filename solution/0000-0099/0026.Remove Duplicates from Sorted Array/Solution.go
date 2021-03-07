func removeDuplicates(nums []int) int {
    cnt := 0
    n := len(nums)
    for i := 1; i < n; i++ {
        if nums[i] == nums[i - 1] {
            cnt++
        } else {
            nums[i - cnt] = nums[i]
        }
    }
    return n - cnt
}