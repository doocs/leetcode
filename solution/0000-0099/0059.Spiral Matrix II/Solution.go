func generateMatrix(n int) [][]int {
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	dirs := [4][2]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	var i, j, k int
	for v := 1; v <= n*n; v++ {
		ans[i][j] = v
		x, y := i+dirs[k][0], j+dirs[k][1]
		if x < 0 || y < 0 || x >= n || y >= n || ans[x][y] > 0 {
			k = (k + 1) % 4
			x, y = i+dirs[k][0], j+dirs[k][1]
		}
		i, j = x, y
	}
	return ans
}