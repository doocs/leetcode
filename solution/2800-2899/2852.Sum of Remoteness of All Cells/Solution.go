func sumRemoteness(grid [][]int) (ans int64) {
	n := len(grid)
	cnt := 0
	for _, row := range grid {
		for _, x := range row {
			if x > 0 {
				cnt++
			}
		}
	}
	var dfs func(i, j int) (int, int)
	dfs = func(i, j int) (int, int) {
		s, t := grid[i][j], 1
		grid[i][j] = 0
		dirs := [5]int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 0 {
				ss, tt := dfs(x, y)
				s += ss
				t += tt
			}
		}
		return s, t
	}
	for i := range grid {
		for j := range grid[i] {
			if grid[i][j] > 0 {
				s, t := dfs(i, j)
				ans += int64(cnt-t) * int64(s)
			}
		}
	}
	return
}