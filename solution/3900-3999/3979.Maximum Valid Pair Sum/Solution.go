func maxValidPairSum(nums []int, k int) int {
	var ans, x int
	for j := k; j < len(nums); j++ {
		y := nums[j]
		x = max(x, nums[j-k])
		ans = max(ans, x+y)
	}
	return ans
}
