func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	bfs := func(i, j int) {
		grid[i][j] = '0'
		q := [][]int{[]int{i, j}}
		dirs := []int{-1, 0, 1, 0, -1}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1' {
					q = append(q, []int{x, y})
					grid[x][y] = '0'
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				bfs(i, j)
				ans++
			}
		}
	}
	return ans
}