func spiralOrder(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	x1, y1, x2, y2 := 0, 0, m-1, n-1
	for x1 <= x2 && y1 <= y2 {
		for j := y1; j <= y2; j++ {
			ans = append(ans, matrix[x1][j])
		}
		for i := x1 + 1; i <= x2; i++ {
			ans = append(ans, matrix[i][y2])
		}
		if x1 < x2 && y1 < y2 {
			for j := y2 - 1; j >= y1; j-- {
				ans = append(ans, matrix[x2][j])
			}
			for i := x2 - 1; i > x1; i-- {
				ans = append(ans, matrix[i][y1])
			}
		}
		x1, y1 = x1+1, y1+1
		x2, y2 = x2-1, y2-1
	}
	return
}