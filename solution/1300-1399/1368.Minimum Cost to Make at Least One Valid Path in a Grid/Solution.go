func minCost(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := doublylinkedlist.New()
	q.Add([]int{0, 0, 0})
	dirs := [][]int{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	for !q.Empty() {
		v, _ := q.Get(0)
		p := v.([]int)
		q.Remove(0)
		i, j, d := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return d
		}
		if vis[i][j] {
			continue
		}
		vis[i][j] = true
		for k := 1; k <= 4; k++ {
			x, y := i+dirs[k][0], j+dirs[k][1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if grid[i][j] == k {
					q.Insert(0, []int{x, y, d})
				} else {
					q.Add([]int{x, y, d + 1})
				}
			}
		}
	}
	return -1
}