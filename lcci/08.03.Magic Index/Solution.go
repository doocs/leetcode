func findMagicIndex(nums []int) int {
	return find(nums, 0, len(nums)-1)
}

func find(nums []int, left, right int) int {
	if left > right {
		return -1
	}
	mid := (left + right) >> 1
	leftIndex := find(nums, left, mid-1)
	if leftIndex != -1 {
		return leftIndex
	}
	if nums[mid] == mid {
		return mid
	}
	return find(nums, mid+1, right)
}