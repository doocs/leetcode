func setZeroes(matrix [][]int) {
	row := make([]bool, len(matrix))
	col := make([]bool, len(matrix[0]))
	for i := range matrix {
		for j, x := range matrix[i] {
			if x == 0 {
				row[i] = true
				col[j] = true
			}
		}
	}
	for i := range matrix {
		for j := range matrix[i] {
			if row[i] || col[j] {
				matrix[i][j] = 0
			}
		}
	}
}
