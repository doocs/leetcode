func findRepeatNumber(nums []int) int {
	for i := 0; ; i++ {
		for nums[i] != i {
			j := nums[i]
			if nums[j] == j {
				return j
			}
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
}