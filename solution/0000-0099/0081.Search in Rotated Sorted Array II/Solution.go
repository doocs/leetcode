func search(nums []int, target int) bool {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[r] {
			if nums[l] <= target && target <= nums[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if nums[mid] < nums[r] {
			if nums[mid] < target && target <= nums[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	return nums[l] == target
}