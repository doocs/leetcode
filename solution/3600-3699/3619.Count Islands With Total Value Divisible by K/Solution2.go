func countIslands(grid [][]int, k int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	stk := [][2]int{}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				continue
			}
			s := grid[i][j]
			grid[i][j] = 0
			stk = append(stk, [2]int{i, j})
			for len(stk) > 0 {
				p := stk[len(stk)-1]
				stk = stk[:len(stk)-1]
				for d := 0; d < 4; d++ {
					x, y := p[0]+dirs[d], p[1]+dirs[d+1]
					if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
						s += grid[x][y]
						grid[x][y] = 0
						stk = append(stk, [2]int{x, y})
					}
				}
			}
			if s%k == 0 {
				ans++
			}
		}
	}
	return
}
