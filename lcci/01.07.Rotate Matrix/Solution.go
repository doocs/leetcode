func rotate(matrix [][]int) {
	n := len(matrix)
	r, c := n/2, (n+1)/2
	for i := 0; i < r; i++ {
		for j := 0; j < c; j++ {
			temp := matrix[i][j]
			matrix[i][j] = matrix[n-j-1][i]
			matrix[n-j-1][i] = matrix[n-i-1][n-j-1]
			matrix[n-i-1][n-j-1] = matrix[j][n-i-1]
			matrix[j][n-i-1] = temp
		}
	}
}
