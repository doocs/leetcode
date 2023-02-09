func dieSimulator(n int, rollMax []int) int {
	f := make([][7][16]int, n)
	const mod = 1e9 + 7
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= n {
			return 1
		}
		if f[i][j][x] != 0 {
			return f[i][j][x]
		}
		ans := 0
		for k := 1; k <= 6; k++ {
			if k != j {
				ans += dfs(i+1, k, 1)
			} else if x < rollMax[j-1] {
				ans += dfs(i+1, j, x+1)
			}
		}
		f[i][j][x] = ans % mod
		return f[i][j][x]
	}
	return dfs(0, 0, 0)
}