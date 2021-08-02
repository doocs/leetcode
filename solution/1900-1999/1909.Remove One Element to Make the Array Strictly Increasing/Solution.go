func canBeIncreasing(nums []int) bool {
	i, n := 1, len(nums)
	for ; i < n && nums[i-1] < nums[i]; i++ {

	}
	return check(nums, i-1) || check(nums, i)
}

func check(nums []int, i int) bool {
	prev := 0
	for j := 0; j < len(nums); j++ {
		if i == j {
			continue
		}
		if prev >= nums[j] {
			return false
		}
		prev = nums[j]
	}
	return true
}