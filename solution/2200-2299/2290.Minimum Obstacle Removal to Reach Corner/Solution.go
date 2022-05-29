func minimumObstacles(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := doublylinkedlist.New()
	q.Add([]int{0, 0, 0})
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for !q.Empty() {
		v, _ := q.Get(0)
		p := v.([]int)
		q.Remove(0)
		i, j, k := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return k
		}
		if vis[i][j] {
			continue
		}
		vis[i][j] = true
		for o := 0; o < 4; o++ {
			x, y := i+dirs[o], j+dirs[o+1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if grid[x][y] == 0 {
					q.Insert(0, []int{x, y, k})
				} else {
					q.Add([]int{x, y, k + 1})
				}
			}
		}
	}
	return 0
}