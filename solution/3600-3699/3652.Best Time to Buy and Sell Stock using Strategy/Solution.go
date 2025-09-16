func maxProfit(prices []int, strategy []int, k int) int64 {
	n := len(prices)
	s := make([]int64, n+1)
	t := make([]int64, n+1)

	for i := 1; i <= n; i++ {
		a := prices[i-1]
		b := strategy[i-1]
		s[i] = s[i-1] + int64(a*b)
		t[i] = t[i-1] + int64(a)
	}

	ans := s[n]
	for i := k; i <= n; i++ {
		ans = max(ans, s[n]-(s[i]-s[i-k])+(t[i]-t[i-k/2]))
	}
	return ans
}
