func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	q := [][2]int{}
	for j := 0; j < n; j++ {
		for _, i := range []int{0, m - 1} {
			if grid[i][j] == 1 {
				q = append(q, [2]int{i, j})
				grid[i][j] = 0
			}
		}
	}
	for i := 0; i < m; i++ {
		for _, j := range []int{0, n - 1} {
			if grid[i][j] == 1 {
				q = append(q, [2]int{i, j})
				grid[i][j] = 0
			}
		}
	}
	for len(q) > 0 {
		i, j := q[0][0], q[0][1]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				q = append(q, [2]int{x, y})
				grid[x][y] = 0
			}
		}
	}
	for _, row := range grid {
		for _, x := range row {
			ans += x
		}
	}
	return
}
