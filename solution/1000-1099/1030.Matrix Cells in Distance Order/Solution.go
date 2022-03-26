func allCellsDistOrder(rows int, cols int, rCenter int, cCenter int) [][]int {
	q := [][]int{{rCenter, cCenter}}
	vis := make([][]bool, rows)
	for i := range vis {
		vis[i] = make([]bool, cols)
	}
	vis[rCenter][cCenter] = true
	var ans [][]int
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			ans = append(ans, p)
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y] {
					q = append(q, []int{x, y})
					vis[x][y] = true
				}
			}
		}
	}
	return ans
}