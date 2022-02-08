func findFarmland(land [][]int) [][]int {
	m, n := len(land), len(land[0])
	var ans [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if land[i][j] == 0 || (j > 0 && land[i][j-1] == 1) || (i > 0 && land[i-1][j] == 1) {
				continue
			}
			x, y := i, j
			for ; x+1 < m && land[x+1][j] == 1; x++ {
			}
			for ; y+1 < n && land[x][y+1] == 1; y++ {
			}
			ans = append(ans, []int{i, j, x, y})
		}
	}
	return ans
}