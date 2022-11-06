func applyOperations(nums []int) (ans []int) {
    n := len(nums)
    for i := 0; i < n - 1; i++ {
        if nums[i] == nums[i + 1] {
            nums[i] <<= 1
            nums[i + 1] = 0
        }
    }
    for _, v := range nums {
        if v != 0 {
            ans = append(ans, v)
        }
    }
    for _, v := range nums {
        if v == 0 {
            ans = append(ans, v)
        }
    }
    return
}