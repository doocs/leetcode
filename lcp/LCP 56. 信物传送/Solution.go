func conveyorBelt(matrix []string, start []int, end []int) int {
	dirs := [5]int{-1, 0, 1, 0, -1}
	d := map[byte]int{
		'^': 0,
		'>': 1,
		'v': 2,
		'<': 3,
	}
	q := [][2]int{[2]int{start[0], start[1]}}
	m, n := len(matrix), len(matrix[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = 1 << 30
		}
	}
	dist[start[0]][start[1]] = 0
	for {
		p := q[0]
		i, j := p[0], p[1]
		if i == end[0] && j == end[1] {
			return dist[i][j]
		}
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			t := dist[i][j]
			if k != d[matrix[i][j]] {
				t++
			}
			if x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y] {
				dist[x][y] = t
				if dist[x][y] == dist[i][j] {
					q = append([][2]int{[2]int{x, y}}, q...)
				} else {
					q = append(q, [2]int{x, y})
				}
			}
		}
	}
}