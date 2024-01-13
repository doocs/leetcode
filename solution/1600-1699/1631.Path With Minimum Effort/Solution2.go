func minimumEffortPath(heights [][]int) int {
	return sort.Search(1e6, func(h int) bool {
		m, n := len(heights), len(heights[0])
		vis := make([][]bool, m)
		for i := range vis {
			vis[i] = make([]bool, n)
		}
		vis[0][0] = true
		q := [][2]int{}
		q = append(q, [2]int{0, 0})
		dirs := [5]int{-1, 0, 1, 0, -1}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if i == m-1 && j == n-1 {
				return true
			}
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && abs(heights[x][y]-heights[i][j]) <= h {
					vis[x][y] = true
					q = append(q, [2]int{x, y})
				}
			}
		}
		return false
	})
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}