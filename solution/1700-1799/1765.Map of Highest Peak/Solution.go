func highestPeak(isWater [][]int) [][]int {
	m, n := len(isWater), len(isWater[0])
	ans := make([][]int, m)
	type pair struct{ i, j int }
	q := []pair{}
	for i, row := range isWater {
		ans[i] = make([]int, n)
		for j, v := range row {
			ans[i][j] = v - 1
			if v == 1 {
				q = append(q, pair{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p.i, p.j
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
				ans[x][y] = ans[i][j] + 1
				q = append(q, pair{x, y})
			}
		}
	}
	return ans
}