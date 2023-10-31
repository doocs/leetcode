func maxSideLength(mat [][]int, threshold int) int {
	m, n := len(mat), len(mat[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + mat[i-1][j-1]
		}
	}
	check := func(k int) bool {
		for i := 0; i < m-k+1; i++ {
			for j := 0; j < n-k+1; j++ {
				if s[i+k][j+k]-s[i][j+k]-s[i+k][j]+s[i][j] <= threshold {
					return true
				}
			}
		}
		return false
	}
	l, r := 0, min(m, n)
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}