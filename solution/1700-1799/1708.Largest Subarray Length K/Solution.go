func largestSubarray(nums []int, k int) []int {
	j := 0
	for i := 1; i < len(nums)-k+1; i++ {
		if nums[j] < nums[i] {
			j = i
		}
	}
	return nums[j : j+k]
}