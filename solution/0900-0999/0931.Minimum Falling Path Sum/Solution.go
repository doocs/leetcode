func minFallingPathSum(matrix [][]int) int {
	n := len(matrix)
	for i := 1; i < n; i++ {
		for j := 0; j < n; j++ {
			mi := matrix[i-1][j]
			if j > 0 && mi > matrix[i-1][j-1] {
				mi = matrix[i-1][j-1]
			}
			if j < n-1 && mi > matrix[i-1][j+1] {
				mi = matrix[i-1][j+1]
			}
			matrix[i][j] += mi
		}
	}
	res := 10000
	for j := 0; j < n; j++ {
		if res > matrix[n-1][j] {
			res = matrix[n-1][j]
		}
	}
	return res
}