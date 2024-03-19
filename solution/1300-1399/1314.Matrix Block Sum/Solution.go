func matrixBlockSum(mat [][]int, k int) [][]int {
	m, n := len(mat), len(mat[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i, row := range mat {
		for j, x := range row {
			s[i+1][j+1] = s[i][j+1] + s[i+1][j] - s[i][j] + x
		}
	}

	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x1 := max(i-k, 0)
			y1 := max(j-k, 0)
			x2 := min(m-1, i+k)
			y2 := min(n-1, j+k)
			ans[i][j] = s[x2+1][y2+1] - s[x1][y2+1] - s[x2+1][y1] + s[x1][y1]
		}
	}

	return ans
}