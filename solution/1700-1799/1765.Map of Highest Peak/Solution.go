func highestPeak(isWater [][]int) [][]int {
	m, n := len(isWater), len(isWater[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
		for j := range ans[i] {
			ans[i][j] = -1
		}
	}
	type pair struct{ i, j int }
	var q []pair
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if isWater[i][j] == 1 {
				ans[i][j] = 0
				q = append(q, pair{i, j})
			}
		}
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		for _, dir := range dirs {
			x, y := p.i+dir[0], p.j+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
				ans[x][y] = ans[p.i][p.j] + 1
				q = append(q, pair{x, y})
			}
		}
	}
	return ans
}