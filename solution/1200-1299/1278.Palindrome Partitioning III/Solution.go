func palindromePartition(s string, k int) int {
	n := len(s)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 1; j < n; j++ {
			if s[i] != s[j] {
				g[i][j] = 1
			}
			if i+1 < j {
				g[i][j] += g[i+1][j-1]
			}
		}
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			if j == 1 {
				f[i][j] = g[0][i-1]
			} else {
				f[i][j] = 100000
				for h := j - 1; h < i; h++ {
					f[i][j] = min(f[i][j], f[h][j-1]+g[h][i-1])
				}
			}
		}
	}
	return f[n][k]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}