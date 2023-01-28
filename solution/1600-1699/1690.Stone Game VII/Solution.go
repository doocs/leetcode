func stoneGameVII(stones []int) int {
	n := len(stones)
	s := make([]int, n+1)
	f := make([][]int, n)
	for i, x := range stones {
		s[i+1] = s[i] + x
		f[i] = make([]int, n)
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		a := s[j+1] - s[i+1] - dfs(i+1, j)
		b := s[j] - s[i] - dfs(i, j-1)
		f[i][j] = max(a, b)
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