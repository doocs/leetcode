func countPaths(grid [][]int) int {
	const mod = 1e9 + 7
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	cells := make([][3]int, 0, m*n)
	for i := 0; i < m; i++ {
		f[i] = make([]int, n)
		for j := 0; j < n; j++ {
			f[i][j] = 1
			cells = append(cells, [3]int{grid[i][j], i, j})
		}
	}
	sort.Slice(cells, func(a, b int) bool { return cells[a][0] > cells[b][0] })
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, cell := range cells {
		i, j := cell[1], cell[2]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y] {
				f[i][j] = (f[i][j] + f[x][y]) % mod
			}
		}
	}
	ans := 0
	for _, row := range f {
		for _, v := range row {
			ans = (ans + v) % mod
		}
	}
	return ans
}
