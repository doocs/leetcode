func hasPath(maze [][]int, start []int, destination []int) bool {
	m, n := len(maze), len(maze[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	vis[start[0]][start[1]] = true
	q := [][]int{start}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		i, j := q[0][0], q[0][1]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i, j
			a, b := dirs[k], dirs[k+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x += a
				y += b
			}
			if x == destination[0] && y == destination[1] {
				return true
			}
			if !vis[x][y] {
				vis[x][y] = true
				q = append(q, []int{x, y})
			}
		}
	}
	return false
}