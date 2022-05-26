func highestRankedKItems(grid [][]int, pricing []int, start []int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	row, col := start[0], start[1]
	low, high := pricing[0], pricing[1]
	var items [][]int
	if low <= grid[row][col] && grid[row][col] <= high {
		items = append(items, []int{0, grid[row][col], row, col})
	}
	q := [][]int{{row, col, 0}}
	grid[row][col] = 0
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j, d := p[0], p[1], p[2]
		for l := 0; l < 4; l++ {
			x, y := i+dirs[l], j+dirs[l+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				if low <= grid[x][y] && grid[x][y] <= high {
					items = append(items, []int{d + 1, grid[x][y], x, y})
				}
				grid[x][y] = 0
				q = append(q, []int{x, y, d + 1})
			}
		}
	}
	sort.Slice(items, func(i, j int) bool {
		a, b := items[i], items[j]
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
	var ans [][]int
	for i := 0; i < len(items) && i < k; i++ {
		ans = append(ans, items[i][2:])
	}
	return ans
}