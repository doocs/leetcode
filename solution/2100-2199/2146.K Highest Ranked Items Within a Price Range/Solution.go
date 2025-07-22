func highestRankedKItems(grid [][]int, pricing []int, start []int, k int) (ans [][]int) {
	m, n := len(grid), len(grid[0])
	row, col := start[0], start[1]
	low, high := pricing[0], pricing[1]
	q := [][2]int{{row, col}}
	pq := [][]int{}
	if low <= grid[row][col] && grid[row][col] <= high {
		pq = append(pq, []int{0, grid[row][col], row, col})
	}
	grid[row][col] = 0
	dirs := [5]int{-1, 0, 1, 0, -1}
	for step := 1; len(q) > 0; step++ {
		for sz := len(q); sz > 0; sz-- {
			x, y := q[0][0], q[0][1]
			q = q[1:]
			for j := 0; j < 4; j++ {
				nx, ny := x+dirs[j], y+dirs[j+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0 {
					if low <= grid[nx][ny] && grid[nx][ny] <= high {
						pq = append(pq, []int{step, grid[nx][ny], nx, ny})
					}
					grid[nx][ny] = 0
					q = append(q, [2]int{nx, ny})
				}
			}
		}
	}
	sort.Slice(pq, func(i, j int) bool {
		a, b := pq[i], pq[j]
		if a[0] != b[0] {
			return a[0] < b[0]
		}
		if a[1] != b[1] {
			return a[1] < b[1]
		}
		if a[2] != b[2] {
			return a[2] < b[2]
		}
		return a[3] < b[3]
	})
	for i := 0; i < len(pq) && i < k; i++ {
		ans = append(ans, pq[i][2:])
	}
	return
}
