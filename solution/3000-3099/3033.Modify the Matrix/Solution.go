func modifiedMatrix(matrix [][]int) [][]int {
  r := len(matrix)
	c := len(matrix[0])
	for i := 0; i < c; i++ {
		maxs := math.MinInt32
		for j := 0; j < r; j++ {
			if matrix[j][i] > maxs {
				maxs = matrix[j][i]
			}
		}
		for j := 0; j < r; j++ {
			if matrix[j][i] == -1 {
				matrix[j][i] = maxs
			}
		}
	}
	return matrix
}
