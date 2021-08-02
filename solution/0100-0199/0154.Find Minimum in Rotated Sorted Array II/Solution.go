func findMin(nums []int) int {
	left, right := 0, len(nums)-1
	for left+1 < right {
		mid := int(uint(left+right) >> 1)
		if nums[mid] > nums[right] {
			left = mid
		} else if nums[mid] < nums[right] {
			right = mid
		} else {
			right--
		}
	}
	if nums[left] < nums[right] {
		return nums[left]
	}
	return nums[right]
}
