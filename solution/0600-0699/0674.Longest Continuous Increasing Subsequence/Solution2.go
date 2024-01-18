func findLengthOfLCIS(nums []int) int {
	ans := 1
	n := len(nums)
	for i := 0; i < n; {
		j := i + 1
		for j < n && nums[j-1] < nums[j] {
			j++
		}
		ans = max(ans, j-i)
		i = j
	}
	return ans
}