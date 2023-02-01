func exchange(nums []int) []int {
	j := 0
	for i, x := range nums {
		if x&1 == 1 {
			nums[i], nums[j] = nums[j], nums[i]
			j++
		}
	}
	return nums
}