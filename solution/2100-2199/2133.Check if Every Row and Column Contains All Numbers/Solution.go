func checkValid(matrix [][]int) bool {
	n := len(matrix)
	for _, row := range matrix {
		vis := make([]bool, n+1)
		for _, x := range row {
			if vis[x] {
				return false
			}
			vis[x] = true
		}
	}
	for j := 0; j < n; j++ {
		vis := make([]bool, n+1)
		for i := 0; i < n; i++ {
			if vis[matrix[i][j]] {
				return false
			}
			vis[matrix[i][j]] = true
		}
	}
	return true
}
