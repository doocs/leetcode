func maximumMinutes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	fire := make([][]bool, m)
	vis := make([][]bool, m)
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := range fire {
		fire[i] = make([]bool, n)
		vis[i] = make([]bool, n)
	}
	l, r := -1, m*n
	spread := func(q [][2]int) [][2]int {
		nq := [][2]int{}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0 {
					fire[x][y] = true
					nq = append(nq, [2]int{x, y})
				}
			}
		}
		return nq
	}
	check := func(t int) bool {
		for i := range fire {
			for j := range fire[i] {
				fire[i][j] = false
				vis[i][j] = false
			}
		}
		q1 := [][2]int{}
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == 1 {
					q1 = append(q1, [2]int{i, j})
					fire[i][j] = true
				}
			}
		}
		for ; t > 0 && len(q1) > 0; t-- {
			q1 = spread(q1)
		}
		q2 := [][2]int{{0, 0}}
		vis[0][0] = true
		for ; len(q2) > 0; q1 = spread(q1) {
			for d := len(q2); d > 0; d-- {
				p := q2[0]
				q2 = q2[1:]
				if fire[p[0]][p[1]] {
					continue
				}
				for k := 0; k < 4; k++ {
					x, y := p[0]+dirs[k], p[1]+dirs[k+1]
					if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y] && grid[x][y] == 0 {
						if x == m-1 && y == n-1 {
							return true
						}
						vis[x][y] = true
						q2 = append(q2, [2]int{x, y})
					}
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
	if l == m*n {
		return int(1e9)
	}
	return l
}