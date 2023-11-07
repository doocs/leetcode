func minimumRightShifts(nums []int) int {
	n := len(nums)
	i := 1
	for i < n && nums[i-1] < nums[i] {
		i++
	}
	k := i + 1
	for k < n && nums[k-1] < nums[k] && nums[k] < nums[0] {
		k++
	}
	if k < n {
		return -1
	}
	return n - i
}