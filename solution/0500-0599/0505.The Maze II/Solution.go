func shortestDistance(maze [][]int, start []int, destination []int) int {
	m, n := len(maze), len(maze[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[start[0]][start[1]] = 0
	q := [][]int{start}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		i, j := q[0][0], q[0][1]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y, step := i, j, dist[i][j]
			a, b := dirs[k], dirs[k+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x, y, step = x+a, y+b, step+1
			}
			if step < dist[x][y] {
				dist[x][y] = step
				q = append(q, []int{x, y})
			}
		}
	}
	if dist[destination[0]][destination[1]] == math.MaxInt32 {
		return -1
	}
	return dist[destination[0]][destination[1]]
}