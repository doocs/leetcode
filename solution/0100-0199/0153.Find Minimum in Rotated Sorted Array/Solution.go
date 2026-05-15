func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[len(nums)-1] {
			l = mid + 1
		} else {
			r = mid
		}
	}
	return nums[l]
}
