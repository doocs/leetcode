func minimumObstacles(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := doublylinkedlist.New()
	type tuple struct{ i, j, k int }
	q.Add(tuple{0, 0, 0})
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for {
		v, _ := q.Get(0)
		p := v.(tuple)
		q.Remove(0)
		i, j, k := p.i, p.j, p.k
		if i == m-1 && j == n-1 {
			return k
		}
		if vis[i][j] {
			continue
		}
		vis[i][j] = true
		for h := 0; h < 4; h++ {
			x, y := i+dirs[h], j+dirs[h+1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if grid[x][y] == 0 {
					q.Insert(0, tuple{x, y, k})
				} else {
					q.Add(tuple{x, y, k + 1})
				}
			}
		}
	}
}