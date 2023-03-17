func numberOfWays(startPos int, endPos int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j || j < 0 {
			return 0
		}
		if j == 0 {
			if i == 0 {
				return 1
			}
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = (dfs(i+1, j-1) + dfs(abs(i-1), j-1)) % mod
		return f[i][j]
	}
	return dfs(abs(startPos-endPos), k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}