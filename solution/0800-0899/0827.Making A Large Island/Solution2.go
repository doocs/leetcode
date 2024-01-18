func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, n)
	}
	cnt := make([]int, n*n+1)
	dirs := []int{-1, 0, 1, 0, -1}
	ans, root := 0, 0

	var dfs func(i, j int)
	dfs = func(i, j int) {
		p[i][j] = root
		cnt[root]++
		ans = max(ans, cnt[root])
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0 {
				dfs(x, y)
			}
		}
	}

	for i, row := range grid {
		for j, v := range row {
			if v == 1 && p[i][j] == 0 {
				root++
				dfs(i, j)
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						root := p[x][y]
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += cnt[root]
						}
					}
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}