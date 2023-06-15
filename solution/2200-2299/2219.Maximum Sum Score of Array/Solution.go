func maximumSumScore(nums []int) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + int64(v)
	}
	var ans int64 = math.MinInt64
	for i := 0; i < n; i++ {
		ans = max(ans, max(s[i+1], s[n]-s[i]))
	}
	return ans
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}