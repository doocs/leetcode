func isMonotonic(nums []int) bool {
	isIncr, isDecr := false, false
	for i, v := range nums[1:] {
		if v < nums[i] {
			isIncr = true
		} else if v > nums[i] {
			isDecr = true
		}
		if isIncr && isDecr {
			return false
		}
	}
	return true
}