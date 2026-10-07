func countSubIslands(grid1 [][]int, grid2 [][]int) (ans int) {
	m, n := len(grid1), len(grid1[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] != 1 {
				continue
			}
			ok := 1
			grid2[i][j] = 0
			stk := [][2]int{{i, j}}
			for len(stk) > 0 {
				cur := stk[len(stk)-1]
				stk = stk[:len(stk)-1]
				ok &= grid1[cur[0]][cur[1]]
				for k := 0; k < 4; k++ {
					x, y := cur[0]+dirs[k], cur[1]+dirs[k+1]
					if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 {
						grid2[x][y] = 0
						stk = append(stk, [2]int{x, y})
					}
				}
			}
			ans += ok
		}
	}
	return
}
