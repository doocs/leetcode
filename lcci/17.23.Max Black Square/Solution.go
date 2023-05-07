func findSquare(matrix [][]int) []int {
	n := len(matrix)
	down := make([][]int, n)
	right := make([][]int, n)
	for i := range down {
		down[i] = make([]int, n)
		right[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if matrix[i][j] == 0 {
				down[i][j], right[i][j] = 1, 1
				if i+1 < n {
					down[i][j] += down[i+1][j]
				}
				if j+1 < n {
					right[i][j] += right[i][j+1]
				}
			}
		}
	}
	for k := n; k > 0; k-- {
		for i := 0; i <= n-k; i++ {
			for j := 0; j <= n-k; j++ {
				if down[i][j] >= k && right[i][j] >= k && right[i+k-1][j] >= k && down[i][j+k-1] >= k {
					return []int{i, j, k}
				}
			}
		}
	}
	return []int{}
}