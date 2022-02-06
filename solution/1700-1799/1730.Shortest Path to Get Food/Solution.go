func getFood(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	pos := func() []int {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == '*' {
					return []int{i, j}
				}
			}
		}
		return []int{}
	}
	q := [][]int{pos()}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	for len(q) > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == '#' {
						return ans
					}
					if grid[x][y] == 'O' {
						grid[x][y] = 'X'
						q = append(q, []int{x, y})
					}
				}
			}
		}
	}
	return -1
}