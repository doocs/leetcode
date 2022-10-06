func largestSubarray(nums []int, k int) []int {
	i, mx := 0, 0
	for j := 0; j < len(nums)-k+1; j++ {
		if mx < nums[j] {
			mx = nums[j]
			i = j
		}
	}
	return nums[i : i+k]
}