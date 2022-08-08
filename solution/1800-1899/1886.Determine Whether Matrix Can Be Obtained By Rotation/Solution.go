func findRotation(mat [][]int, target [][]int) bool {
	n := len(mat)
	for k := 0; k < 4; k++ {
		g := make([][]int, n)
		for i := range g {
			g[i] = make([]int, n)
		}
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				g[i][j] = mat[j][n-i-1]
			}
		}
		if equals(g, target) {
			return true
		}
		mat = g
	}
	return false
}

func equals(a, b [][]int) bool {
	for i, row := range a {
		for j, v := range row {
			if v != b[i][j] {
				return false
			}
		}
	}
	return true
}