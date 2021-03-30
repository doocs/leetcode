func minArray(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := l + (r-l)>>1
		if nums[mid] > nums[r] {
			l = mid + 1
		} else if nums[mid] < nums[r] {
			r = mid // r 本身不需要被排除
		} else {
			r--
		}
	}
	return nums[l]
}