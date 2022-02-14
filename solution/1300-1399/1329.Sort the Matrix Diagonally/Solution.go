func diagonalSort(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	for k := 0; k < m-1 && k < n-1; k++ {
		for i := 0; i < m-1; i++ {
			for j := 0; j < n-1; j++ {
				if mat[i][j] > mat[i+1][j+1] {
					mat[i][j], mat[i+1][j+1] = mat[i+1][j+1], mat[i][j]
				}
			}
		}
	}
	return mat
}