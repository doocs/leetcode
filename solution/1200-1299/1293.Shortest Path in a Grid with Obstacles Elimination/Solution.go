func shortestPath(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	if k >= m+n-3 {
		return m + n - 2
	}
	q := [][]int{[]int{0, 0, k}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, k+1)
		}
	}
	vis[0][0][k] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	for len(q) > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			k = p[2]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if x == m-1 && y == n-1 {
						return ans
					}
					if grid[x][y] == 0 && !vis[x][y][k] {
						q = append(q, []int{x, y, k})
						vis[x][y][k] = true
					} else if grid[x][y] == 1 && k > 0 && !vis[x][y][k-1] {
						q = append(q, []int{x, y, k - 1})
						vis[x][y][k-1] = true
					}
				}
			}
		}
	}
	return -1
}