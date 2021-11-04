func islandPerimeter(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				ans += 4
				if i < m-1 && grid[i+1][j] == 1 {
					ans -= 2
				}
				if j < n-1 && grid[i][j+1] == 1 {
					ans -= 2
				}
			}
		}
	}
	return ans
}