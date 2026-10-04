func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	flood := func(i, j int) {
		grid[i][j] = 0
		stk := [][2]int{{i, j}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for k := 0; k < 4; k++ {
				x, y := cur[0]+dirs[k], cur[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
					grid[x][y] = 0
					stk = append(stk, [2]int{x, y})
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		for _, i := range [2]int{0, m - 1} {
			if grid[i][j] == 1 {
				flood(i, j)
			}
		}
	}
	for i := 0; i < m; i++ {
		for _, j := range [2]int{0, n - 1} {
			if grid[i][j] == 1 {
				flood(i, j)
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
