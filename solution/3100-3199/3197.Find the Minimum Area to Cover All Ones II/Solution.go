func minimumSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	ans := m * n
	inf := math.MaxInt32

	f := func(i1, j1, i2, j2 int) int {
		x1, y1 := inf, inf
		x2, y2 := -inf, -inf
		for i := i1; i <= i2; i++ {
			for j := j1; j <= j2; j++ {
				if grid[i][j] == 1 {
					x1 = min(x1, i)
					y1 = min(y1, j)
					x2 = max(x2, i)
					y2 = max(y2, j)
				}
			}
		}
		if x1 == inf {
			return 0
		}
		return (x2 - x1 + 1) * (y2 - y1 + 1)
	}

	for i1 := 0; i1 < m-1; i1++ {
		for i2 := i1 + 1; i2 < m-1; i2++ {
			ans = min(ans, f(0, 0, i1, n-1)+f(i1+1, 0, i2, n-1)+f(i2+1, 0, m-1, n-1))
		}
	}

	for j1 := 0; j1 < n-1; j1++ {
		for j2 := j1 + 1; j2 < n-1; j2++ {
			ans = min(ans, f(0, 0, m-1, j1)+f(0, j1+1, m-1, j2)+f(0, j2+1, m-1, n-1))
		}
	}

	for i := 0; i < m-1; i++ {
		for j := 0; j < n-1; j++ {
			ans = min(ans, f(0, 0, i, j)+f(0, j+1, i, n-1)+f(i+1, 0, m-1, n-1))
			ans = min(ans, f(0, 0, i, n-1)+f(i+1, 0, m-1, j)+f(i+1, j+1, m-1, n-1))
			ans = min(ans, f(0, 0, i, j)+f(i+1, 0, m-1, j)+f(0, j+1, m-1, n-1))
			ans = min(ans, f(0, 0, m-1, j)+f(0, j+1, i, n-1)+f(i+1, j+1, m-1, n-1))
		}
	}

	return ans
}