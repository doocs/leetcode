func shortestBridge(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	find := func() []int {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == 1 {
					return []int{i, j}
				}
			}
		}
		return []int{0, 0}
	}
	start := find()
	var q [][]int
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = 2
		q = append(q, []int{i, j})
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	dfs(start[0], start[1])
	ans := -1
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for i := 0; i < 4; i++ {
				x, y := p[0]+dirs[i], p[1]+dirs[i+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == 1 {
						return ans
					}
					if grid[x][y] == 0 {
						grid[x][y] = 2
						q = append(q, []int{x, y})
					}
				}
			}
		}
	}
	return 0
}