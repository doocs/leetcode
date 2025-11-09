func maxPathScore(grid [][]int, k int) int {
	m := len(grid)
	n := len(grid[0])
	inf := 1 << 30

	f := make([][][]int, m)
	for i := 0; i < m; i++ {
		f[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]int, k+1)
			for t := 0; t <= k; t++ {
				f[i][j][t] = -1
			}
		}
	}

	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 || j < 0 || k < 0 {
			return -inf
		}
		if i == 0 && j == 0 {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}

		res := grid[i][j]
		nk := k
		if grid[i][j] != 0 {
			nk--
		}

		a := dfs(i-1, j, nk)
		b := dfs(i, j-1, nk)
		res += max(a, b)

		f[i][j][k] = res
		return res
	}

	ans := dfs(m-1, n-1, k)
	if ans < 0 {
		return -1
	}
	return ans
}
