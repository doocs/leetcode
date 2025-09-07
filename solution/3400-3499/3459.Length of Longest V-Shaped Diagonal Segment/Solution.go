func lenOfVDiagonal(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{1, 1, -1, -1, 1}
	f := make([][][4][2]int, m)
	for i := range f {
		f[i] = make([][4][2]int, n)
	}

	var dfs func(i, j, k, cnt int) int
	dfs = func(i, j, k, cnt int) int {
		if f[i][j][k][cnt] != 0 {
			return f[i][j][k][cnt]
		}

		x := i + dirs[k]
		y := j + dirs[k+1]

		var target int
		if grid[i][j] == 1 {
			target = 2
		} else {
			target = 2 - grid[i][j]
		}

		if x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target {
			f[i][j][k][cnt] = 0
			return 0
		}

		res := dfs(x, y, k, cnt)
		if cnt > 0 {
			res = max(res, dfs(x, y, (k+1)%4, 0))
		}
		f[i][j][k][cnt] = res + 1
		return res + 1
	}

	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for k := 0; k < 4; k++ {
					ans = max(ans, dfs(i, j, k, 1)+1)
				}
			}
		}
	}
	return ans
}
