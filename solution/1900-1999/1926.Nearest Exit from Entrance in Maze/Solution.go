func nearestExit(maze [][]byte, entrance []int) int {
	m, n := len(maze), len(maze[0])
	q := [][]int{entrance}
	maze[entrance[0]][entrance[1]] = '+'
	ans := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for l := 0; l < 4; l++ {
				x, y := p[0]+dirs[l], p[1]+dirs[l+1]
				if x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.' {
					if x == 0 || x == m-1 || y == 0 || y == n-1 {
						return ans
					}
					q = append(q, []int{x, y})
					maze[x][y] = '+'
				}
			}
		}
	}
	return -1
}