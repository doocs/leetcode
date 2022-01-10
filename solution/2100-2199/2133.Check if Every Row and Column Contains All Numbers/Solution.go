func checkValid(matrix [][]int) bool {
	n := len(matrix)
	for i := 0; i < n; i++ {
		seen := make([]bool, n)
		for j := 0; j < n; j++ {
			v := matrix[i][j] - 1
			if seen[v] {
				return false
			}
			seen[v] = true
		}
	}
	for j := 0; j < n; j++ {
		seen := make([]bool, n)
		for i := 0; i < n; i++ {
			v := matrix[i][j] - 1
			if seen[v] {
				return false
			}
			seen[v] = true
		}
	}
	return true
}