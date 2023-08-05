func extractMantra(matrix []string, mantra string) (ans int) {
	m, n, l := len(matrix), len(matrix[0]), len(mantra)
	q := [][3]int{[3]int{0, 0, 0}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, l+1)
		}
	}
	vis[0][0][0] = true
	dirs := [5]int{-1, 0, 1, 0, -1}
	for ; len(q) > 0; ans++ {
		for size := len(q); size > 0; size-- {
			p := q[0]
			q = q[1:]
			i, j, k := p[0], p[1], p[2]
			if k == l {
				return ans
			}
			if matrix[i][j] == mantra[k] && !vis[i][j][k+1] {
				vis[i][j][k+1] = true
				q = append(q, [3]int{i, j, k + 1})
			} else {
				for c := 0; c < 4; c++ {
					x, y := i+dirs[c], j+dirs[c+1]
					if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k] {
						vis[x][y][k] = true
						q = append(q, [3]int{x, y, k})
					}
				}
			}
		}
	}
	return -1
}