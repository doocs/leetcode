func numberWays(hats [][]int) int {
	d := make([][]int, 41)
	mx := 0
	for i, h := range hats {
		for _, v := range h {
			d[v] = append(d[v], i)
			mx = max(mx, v)
		}
	}
	dp := make([][]int, mx+1)
	n := len(hats)
	for i := range dp {
		dp[i] = make([]int, 1<<n)
	}
	dp[0][0] = 1
	mod := int(1e9) + 7
	for i := 1; i <= mx; i++ {
		for mask := 0; mask < 1<<n; mask++ {
			dp[i][mask] = dp[i-1][mask]
			for _, j := range d[i] {
				if ((mask >> j) & 1) == 1 {
					dp[i][mask] = (dp[i][mask] + dp[i-1][mask^(1<<j)]) % mod
				}
			}
		}
	}
	return dp[mx][(1<<n)-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}