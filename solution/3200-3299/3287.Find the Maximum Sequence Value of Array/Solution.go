func maxValue(nums []int, k int) int {
	m := 1 << 7
	n := len(nums)

	f := make([][][]bool, n+1)
	for i := range f {
		f[i] = make([][]bool, k+2)
		for j := range f[i] {
			f[i][j] = make([]bool, m)
		}
	}
	f[0][0][0] = true

	for i := 0; i < n; i++ {
		for j := 0; j <= k; j++ {
			for x := 0; x < m; x++ {
				if f[i][j][x] {
					f[i+1][j][x] = true
					f[i+1][j+1][x|nums[i]] = true
				}
			}
		}
	}

	g := make([][][]bool, n+1)
	for i := range g {
		g[i] = make([][]bool, k+2)
		for j := range g[i] {
			g[i][j] = make([]bool, m)
		}
	}
	g[n][0][0] = true

	for i := n; i > 0; i-- {
		for j := 0; j <= k; j++ {
			for y := 0; y < m; y++ {
				if g[i][j][y] {
					g[i-1][j][y] = true
					g[i-1][j+1][y|nums[i-1]] = true
				}
			}
		}
	}

	ans := 0

	for i := k; i <= n-k; i++ {
		for x := 0; x < m; x++ {
			if f[i][k][x] {
				for y := 0; y < m; y++ {
					if g[i][k][y] {
						ans = max(ans, x^y)
					}
				}
			}
		}
	}

	return ans
}
