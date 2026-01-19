func minCost(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	inf := int(^uint(0)>>1) / 4

	f := make([][][]int, k+1)
	for t := 0; t <= k; t++ {
		f[t] = make([][]int, m)
		for i := 0; i < m; i++ {
			f[t][i] = make([]int, n)
			for j := 0; j < n; j++ {
				f[t][i][j] = inf
			}
		}
	}

	f[0][0][0] = 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				f[0][i][j] = min(f[0][i][j], f[0][i-1][j]+grid[i][j])
			}
			if j > 0 {
				f[0][i][j] = min(f[0][i][j], f[0][i][j-1]+grid[i][j])
			}
		}
	}

	g := make(map[int][][]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := grid[i][j]
			g[x] = append(g[x], []int{i, j})
		}
	}

	keys := make([]int, 0, len(g))
	for key := range g {
		keys = append(keys, key)
	}
	sort.Sort(sort.Reverse(sort.IntSlice(keys)))

	for t := 1; t <= k; t++ {
		mn := inf
		for _, key := range keys {
			pos := g[key]
			for _, p := range pos {
				mn = min(mn, f[t-1][p[0]][p[1]])
			}
			for _, p := range pos {
				f[t][p[0]][p[1]] = mn
			}
		}
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if i > 0 {
					f[t][i][j] = min(f[t][i][j], f[t][i-1][j]+grid[i][j])
				}
				if j > 0 {
					f[t][i][j] = min(f[t][i][j], f[t][i][j-1]+grid[i][j])
				}
			}
		}
	}

	ans := inf
	for t := 0; t <= k; t++ {
		ans = min(ans, f[t][m-1][n-1])
	}
	return ans
}
