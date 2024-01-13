func stoneGame(piles []int) bool {
	n := len(piles)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] == 0 {
			f[i][j] = max(piles[i]-dfs(i+1, j), piles[j]-dfs(i, j-1))
		}
		return f[i][j]
	}
	return dfs(0, n-1) > 0
}