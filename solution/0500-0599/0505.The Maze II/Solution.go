func shortestDistance(maze [][]int, start []int, destination []int) int {
	m, n := len(maze), len(maze[0])
	dist := make([][]int, m)
	const inf = 1 << 30
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}
	dist[start[0]][start[1]] = 0
	q := [][]int{start}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for d := 0; d < 4; d++ {
			x, y, k := i, j, dist[i][j]
			a, b := dirs[d], dirs[d+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x, y, k = x+a, y+b, k+1
			}
			if k < dist[x][y] {
				dist[x][y] = k
				q = append(q, []int{x, y})
			}
		}
	}
	di, dj := destination[0], destination[1]
	if dist[di][dj] == inf {
		return -1
	}
	return dist[di][dj]
}