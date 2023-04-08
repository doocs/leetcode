func mctFromLeafValues(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range g {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
		g[i][i] = arr[i]
		for j := i + 1; j < n; j++ {
			g[i][j] = max(g[i][j-1], arr[j])
		}
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i == j {
			return 0
		}
		if f[i][j] > 0 {
			return f[i][j]
		}
		f[i][j] = 1 << 30
		for k := i; k < j; k++ {
			f[i][j] = min(f[i][j], dfs(i, k)+dfs(k+1, j)+g[i][k]*g[k+1][j])
		}
		return f[i][j]
	}
	return dfs(0, n-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}