func maxSideLength(mat [][]int, threshold int) int {
	m, n := len(mat), len(mat[0])
	s := make([][]int, 310)
	for i := 0; i < len(s); i++ {
		s[i] = make([]int, 310)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j+1] = s[i][j+1] + s[i+1][j] - s[i][j] + mat[i][j]
		}
	}
	left, right := 0, min(m, n)
	check := func(l int) bool {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if i+l-1 < m && j+l-1 < n {
					i1, j1 := i+l-1, j+l-1
					t := s[i1+1][j1+1] - s[i1+1][j] - s[i][j1+1] + s[i][j]
					if t <= threshold {
						return true
					}
				}
			}
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}