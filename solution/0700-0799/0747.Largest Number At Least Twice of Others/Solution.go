func dominantIndex(nums []int) int {
	maxIndex, n := 0, len(nums)
	for i := 1; i < n; i++ {
		if nums[i] > nums[maxIndex] {
			maxIndex = i
		}
	}
	for i := 0; i < n; i++ {
		if i != maxIndex && nums[i]*2 > nums[maxIndex] {
			return -1
		}
	}
	return maxIndex
}