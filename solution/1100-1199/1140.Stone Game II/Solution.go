func stoneGameII(piles []int) int {
	n := len(piles)
	s := make([]int, n+1)
	f := make([][]int, n+1)
	for i, x := range piles {
		s[i+1] = s[i] + x
		f[i] = make([]int, n+1)
	}
	var dfs func(i, m int) int
	dfs = func(i, m int) int {
		if m*2 >= n-i {
			return s[n] - s[i]
		}
		if f[i][m] > 0 {
			return f[i][m]
		}
		f[i][m] = 0
		for x := 1; x <= m<<1; x++ {
			f[i][m] = max(f[i][m], s[n]-s[i]-dfs(i+x, max(m, x)))
		}
		return f[i][m]
	}
	return dfs(0, 1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}