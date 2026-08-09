func maxArea(mat [][]int) int {
	return max(calc(mat), calc(transpose(mat)))
}

func calc(mat [][]int) int {
	m, n := len(mat), len(mat[0])

	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	g := make([]int, m+1)
	suf := make([]int, m+1)

	for i := m - 1; i > 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if mat[i][j] != 0 {
				f[i][j] = min(
					f[i+1][j],
					f[i][j+1],
					f[i+1][j+1],
				) + 1
				if f[i][j] > g[i] {
					g[i] = f[i][j]
				}
			}
		}
		suf[i] = max(suf[i+1], g[i])
	}

	f = make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	g = make([]int, m+1)
	pre := make([]int, m+1)

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if mat[i-1][j-1] != 0 {
				f[i][j] = min(
					f[i-1][j],
					f[i][j-1],
					f[i-1][j-1],
				) + 1
				if f[i][j] > g[i] {
					g[i] = f[i][j]
				}
			}
		}
		pre[i] = max(pre[i-1], g[i])
	}

	ans := 0
	for i := 1; i < m; i++ {
		t := min(pre[i], suf[i])
		if t*t > ans {
			ans = t * t
		}
	}
	return ans
}

func transpose(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans[j][i] = mat[i][j]
		}
	}
	return ans
}
