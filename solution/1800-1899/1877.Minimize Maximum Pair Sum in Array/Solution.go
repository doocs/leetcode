func minPairSum(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i, x := range nums[:n>>1] {
		ans = max(ans, x+nums[n-1-i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}