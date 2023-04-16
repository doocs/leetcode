func findPrefixScore(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	mx := 0
	for i, x := range nums {
		mx = max(mx, x)
		ans[i] = int64(x + mx)
		if i > 0 {
			ans[i] += ans[i-1]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}