func latestDayToCross(row int, col int, cells [][]int) int {
	l, r := 1, len(cells)
	dirs := [5]int{-1, 0, 1, 0, -1}
	check := func(k int) bool {
		g := make([][]int, row)
		for i := range g {
			g[i] = make([]int, col)
		}
		for i := 0; i < k; i++ {
			g[cells[i][0]-1][cells[i][1]-1] = 1
		}
		q := [][2]int{}
		for j := 0; j < col; j++ {
			if g[0][j] == 0 {
				g[0][j] = 1
				q = append(q, [2]int{0, j})
			}
		}
		for len(q) > 0 {
			x, y := q[0][0], q[0][1]
			q = q[1:]
			if x == row-1 {
				return true
			}
			for i := 0; i < 4; i++ {
				nx, ny := x+dirs[i], y+dirs[i+1]
				if nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] == 0 {
					g[nx][ny] = 1
					q = append(q, [2]int{nx, ny})
				}
			}
		}
		return false
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
