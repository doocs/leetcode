func spiralOrder(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	ans := make([]int, 0, m*n)

	top, bottom, left, right := 0, m-1, 0, n-1
	for left <= right && top <= bottom {
		for i := left; i <= right; i++ {
			ans = append(ans, matrix[top][i])
		}
		for i := top + 1; i <= bottom; i++ {
			ans = append(ans, matrix[i][right])
		}
		if left < right && top < bottom {
			for i := right - 1; i >= left; i-- {
				ans = append(ans, matrix[bottom][i])
			}
			for i := bottom - 1; i > top; i-- {
				ans = append(ans, matrix[i][left])
			}
		}
		top++
		bottom--
		left++
		right--
	}

	return ans
}
