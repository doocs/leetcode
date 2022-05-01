func maximumMinutes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}

	spread := func(fire [][]bool, q [][]int) [][]int {
		nf := [][]int{}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0 {
					fire[x][y] = true
					nf = append(nf, []int{x, y})
				}
			}
		}
		return nf
	}

	check := func(t int) bool {
		fire := make([][]bool, m)
		vis := make([][]bool, m)
		f := [][]int{}
		for i, row := range grid {
			fire[i] = make([]bool, n)
			vis[i] = make([]bool, n)
			for j, v := range row {
				if v == 1 {
					fire[i][j] = true
					f = append(f, []int{i, j})
				}
			}
		}
		for t > 0 && len(f) > 0 {
			f = spread(fire, f)
			t--
		}
		if fire[0][0] {
			return false
		}
		q := [][]int{{0, 0}}
		vis[0][0] = true
		for len(q) > 0 {
			for i := len(q); i > 0; i-- {
				p := q[0]
				q = q[1:]
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
						q = append(q, []int{x, y})
					}
				}
			}
			f = spread(fire, f)
		}
		return false
	}

	left, right := -1, m*n
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	if left == m*n {
		return int(1e9)
	}
	return left
}