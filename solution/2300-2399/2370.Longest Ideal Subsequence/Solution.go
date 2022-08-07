func longestIdealString(s string, k int) int {
	n := len(s)
	ans := 1
	dp := make([]int, n)
	for i := range dp {
		dp[i] = 1
	}
	d := map[byte]int{s[0]: 0}
	for i := 1; i < n; i++ {
		a := s[i]
		for b := byte('a'); b <= byte('z'); b++ {
			if int(a)-int(b) > k || int(b)-int(a) > k {
				continue
			}
			if v, ok := d[b]; ok {
				dp[i] = max(dp[i], dp[v]+1)
			}
		}
		d[a] = i
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}