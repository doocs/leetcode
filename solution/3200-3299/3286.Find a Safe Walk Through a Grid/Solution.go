func findSafeWalk(grid [][]int, health int) bool {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[0][0] = grid[0][0]
	q := [][2]int{{0, 0}}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		curr := q[0]
		q = q[1:]
		x, y := curr[0], curr[1]
		for i := 0; i < 4; i++ {
			nx, ny := x+dirs[i], y+dirs[i+1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && dist[nx][ny] > dist[x][y]+grid[nx][ny] {
				dist[nx][ny] = dist[x][y] + grid[nx][ny]
				q = append(q, [2]int{nx, ny})
			}
		}
	}
	return dist[m-1][n-1] < health
}
