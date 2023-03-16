func mergeStones(stones []int, K int) int {
	n := len(stones)
	if (n-1)%(K-1) != 0 {
		return -1
	}
	s := make([]int, n+1)
	for i, x := range stones {
		s[i+1] = s[i] + x
	}
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, K+1)
			for k := range f[i][j] {
				f[i][j][k] = 1 << 20
			}
		}
	}
	for i := 1; i <= n; i++ {
		f[i][i][1] = 0
	}
	for l := 2; l <= n; l++ {
		for i := 1; i <= n-l+1; i++ {
			j := i + l - 1
			for k := 2; k <= K; k++ {
				for h := i; h < j; h++ {
					f[i][j][k] = min(f[i][j][k], f[i][h][k-1]+f[h+1][j][1])
				}
			}
			f[i][j][1] = f[i][j][K] + s[j] - s[i-1]
		}
	}
	return f[1][n][1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}