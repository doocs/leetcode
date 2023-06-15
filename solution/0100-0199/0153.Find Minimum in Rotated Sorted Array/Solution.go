func findMin(nums []int) int {
	n := len(nums)
	if nums[0] <= nums[n-1] {
		return nums[0]
	}
	left, right := 0, n-1
	for left < right {
		mid := (left + right) >> 1
		if nums[0] <= nums[mid] {
			left = mid + 1
		} else {
			right = mid
		}
	}
	return nums[left]
}