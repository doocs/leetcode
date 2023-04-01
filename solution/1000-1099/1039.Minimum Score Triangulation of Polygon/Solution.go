func minScoreTriangulation(values []int) int {
	n := len(values)
	f := [50][50]int{}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i+1 == j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		f[i][j] = 1 << 30
		for k := i + 1; k < j; k++ {
			f[i][j] = min(f[i][j], dfs(i, k)+dfs(k, j)+values[i]*values[k]*values[j])
		}
		return f[i][j]
	}
	return dfs(0, n-1)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}