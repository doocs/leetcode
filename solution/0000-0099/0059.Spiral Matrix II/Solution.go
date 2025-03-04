func generateMatrix(n int) [][]int {
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	i, j, k := 0, 0, 0
	for v := 1; v <= n*n; v++ {
		ans[i][j] = v
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0 {
			k = (k + 1) % 4
		}
		i += dirs[k]
		j += dirs[k+1]
	}
	return ans
}
