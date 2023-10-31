func paintWalls(cost []int, time []int) int {
	n := len(cost)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n<<1|1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if n-i <= j-n {
			return 0
		}
		if i >= n {
			return 1 << 30
		}
		if f[i][j] == -1 {
			f[i][j] = min(dfs(i+1, j+time[i])+cost[i], dfs(i+1, j-1))
		}
		return f[i][j]
	}
	return dfs(0, n)
}