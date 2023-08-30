func minimumOperations(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	vis := map[int]bool{}
	match := make([]int, m*n)
	for i := range match {
		match[i] = -1
	}
	g := map[int][]int{}
	for i, row := range grid {
		for j, v := range row {
			if (i+j)&1 == 1 && v == 1 {
				x := i*n + j
				if i < m-1 && grid[i+1][j] == 1 {
					g[x] = append(g[x], x+n)
				}
				if i > 0 && grid[i-1][j] == 1 {
					g[x] = append(g[x], x-n)
				}
				if j < n-1 && grid[i][j+1] == 1 {
					g[x] = append(g[x], x+1)
				}
				if j > 0 && grid[i][j-1] == 1 {
					g[x] = append(g[x], x-1)
				}
			}
		}
	}
	var find func(int) int
	find = func(i int) int {
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				if match[j] == -1 || find(match[j]) == 1 {
					match[j] = i
					return 1
				}
			}
		}
		return 0
	}
	for i := range g {
		ans += find(i)
		vis = map[int]bool{}
	}
	return
}