func isMonotonic(nums []int) bool {
	incr, decr := true, true
	for i, v := range nums[1:] {
		if !incr && !decr {
			return false
		}
		if v < nums[i] {
			incr = false
		} else if v > nums[i] {
			decr = false
		}
	}
	return incr || decr
}