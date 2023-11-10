func minimizeArrayValue(nums []int) int {
	check := func(mx int) bool {
		d := 0
		for i := len(nums) - 1; i > 0; i-- {
			d = max(0, nums[i]+d-mx)
		}
		return nums[0]+d <= mx
	}

	left, right := 0, slices.Max(nums)
	for left < right {
		mid := (left + right) >> 1
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}