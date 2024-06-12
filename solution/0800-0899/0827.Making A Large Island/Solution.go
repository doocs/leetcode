func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, n)
	}
	cnt := make([]int, n*n+1)
	dirs := []int{-1, 0, 1, 0, -1}
	root := 0
	ans := 0

	var dfs func(int, int) int
	dfs = func(i, j int) int {
		p[i][j] = root
		cnt[root]++
		for k := 0; k < 4; k++ {
			x := i + dirs[k]
			y := j + dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0 {
				dfs(x, y)
			}
		}
		return cnt[root]
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && p[i][j] == 0 {
				root++
				ans = max(ans, dfs(i, j))
			}
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				s := make(map[int]struct{})
				for k := 0; k < 4; k++ {
					x := i + dirs[k]
					y := j + dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						s[p[x][y]] = struct{}{}
					}
				}
				t := 1
				for x := range s {
					t += cnt[x]
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}