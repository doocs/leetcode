func generateMatrix(n int) [][]int {
	res := make([][]int, n)
	for i := range res {
		res[i] = make([]int, n)
	}
	elem := 1
	top, bottom, left, right := 0, n-1, 0, n-1
	for elem <= n*n {
		for i := left; i <= right; i++ {
			res[top][i], elem = elem, elem+1
		}
		top++
		for i := top; i <= bottom; i++ {
			res[i][right], elem = elem, elem+1
		}
		right--
		if top <= bottom {
			for i := right; i >= left; i-- {
				res[bottom][i], elem = elem, elem+1
			}
			bottom--
		}
		if left <= right {
			for i := bottom; i >= top; i-- {
				res[i][left], elem = elem, elem+1
			}
			left++
		}
	}
	return res
}