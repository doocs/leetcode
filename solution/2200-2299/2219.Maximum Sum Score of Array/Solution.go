func maximumSumScore(nums []int) int64 {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	ans := math.MinInt64
	for _, x := range nums {
		l += x
		ans = max(ans, max(l, r))
		r -= x
	}
	return int64(ans)
}
