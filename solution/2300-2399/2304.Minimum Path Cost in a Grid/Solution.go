func minPathCost(grid [][]int, moveCost [][]int) int {
	n := len(grid[0])
	inf := 0x3f3f3f3f
	f := make([]int, n)
	for i, row := range grid {
		g := make([]int, n)
		for j, v := range row {
			g[j] = v
			t := inf
			if i > 0 {
				for k := 0; k < n; k++ {
					t = min(t, f[k]+moveCost[grid[i-1][k]][j])
				}
			}
			if t != inf {
				g[j] += t
			}
		}
		f = g
	}
	ans := inf
	for _, v := range f {
		ans = min(ans, v)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}