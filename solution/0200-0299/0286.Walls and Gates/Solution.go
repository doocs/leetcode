func wallsAndGates(rooms [][]int) {
	m, n := len(rooms), len(rooms[0])
	var q [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if rooms[i][j] == 0 {
				q = append(q, []int{i, j})
			}
		}
	}
	d := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		d++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == math.MaxInt32 {
					rooms[x][y] = d
					q = append(q, []int{x, y})
				}
			}
		}
	}
}