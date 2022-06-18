func minOperations(nums []int) int {
	ans, mx := 0, 0
	for _, v := range nums {
		ans += max(0, mx+1-v)
		mx = max(mx+1, v)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}