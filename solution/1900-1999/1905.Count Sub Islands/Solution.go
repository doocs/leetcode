func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	count := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n) {
				count++
			}
		}
	}
	return count
}

func dfs(grid1 [][]int, grid2 [][]int, i, j, m, n int) bool {
	res := grid1[i][j] == 1
	grid2[i][j] = 0
	directions := [4][2]int{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	for _, direction := range directions {
		a, b := i+direction[0], j+direction[1]
		if a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1 {
			if !dfs(grid1, grid2, a, b, m, n) {
				res = false
			}
		}
	}
	return res
}