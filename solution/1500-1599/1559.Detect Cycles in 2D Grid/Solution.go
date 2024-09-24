func containsCycle(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if !vis[i][j] {
				q := [][]int{{i, j, -1, -1}}
				vis[i][j] = true

				for len(q) > 0 {
					p := q[0]
					q = q[1:]
					x, y, px, py := p[0], p[1], p[2], p[3]

					for k := 0; k < 4; k++ {
						nx, ny := x+dirs[k], y+dirs[k+1]
						if nx >= 0 && nx < m && ny >= 0 && ny < n {
							if grid[nx][ny] != grid[x][y] || (nx == px && ny == py) {
								continue
							}
							if vis[nx][ny] {
								return true
							}
							q = append(q, []int{nx, ny, x, y})
							vis[nx][ny] = true
						}
					}
				}
			}
		}
	}
	return false
}
