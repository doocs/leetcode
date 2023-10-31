func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	q := [][2]int{}
	for i := range dist {
		dist[i] = make([]int, n)
		q = append(q, [2]int{i, 0})
	}
	dirs := [][2]int{{-1, 1}, {0, 1}, {1, 1}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if 0 <= x && x < m && 0 <= y && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j]+1 {
				dist[x][y] = dist[i][j] + 1
				ans = max(ans, dist[x][y])
				q = append(q, [2]int{x, y})
			}
		}
	}
	return
}