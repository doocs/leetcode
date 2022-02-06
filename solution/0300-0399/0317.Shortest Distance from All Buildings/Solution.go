func shortestDistance(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var q [][]int
	total := 0
	cnt := make([][]int, m)
	dist := make([][]int, m)
	for i := range cnt {
		cnt[i] = make([]int, n)
		dist[i] = make([]int, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				total++
				q = append(q, []int{i, j})
				vis := make([]bool, m*n)
				d := 0
				for len(q) > 0 {
					d++
					for k := len(q); k > 0; k-- {
						p := q[0]
						q = q[1:]
						for l := 0; l < 4; l++ {
							x, y := p[0]+dirs[l], p[1]+dirs[l+1]
							if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !vis[x*n+y] {
								cnt[x][y]++
								dist[x][y] += d
								q = append(q, []int{x, y})
								vis[x*n+y] = true
							}
						}
					}
				}
			}
		}
	}

	ans := math.MaxInt32
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 && cnt[i][j] == total {
				if ans > dist[i][j] {
					ans = dist[i][j]
				}
			}
		}
	}
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}