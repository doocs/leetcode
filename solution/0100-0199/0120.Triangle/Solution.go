func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	for i := 1; i < n; i++ {
		for j := 0; j < i+1; j++ {
			mi := 2000000
			if j > 0 && mi > triangle[i-1][j-1] {
				mi = triangle[i-1][j-1]
			}
			if j < i && mi > triangle[i-1][j] {
				mi = triangle[i-1][j]
			}
			triangle[i][j] += mi
		}
	}

	res := 2000000
	for j := 0; j < n; j++ {
		if res > triangle[n-1][j] {
			res = triangle[n-1][j]
		}
	}
	return res
}