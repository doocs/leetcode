func isIdealPermutation(nums []int) bool {
	mx := 0
	for i := 2; i < len(nums); i++ {
		mx = max(mx, nums[i-2])
		if mx > nums[i] {
			return false
		}
	}
	return true
}