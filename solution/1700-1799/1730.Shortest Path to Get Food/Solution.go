func getFood(grid [][]byte) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	type pair struct{ i, j int }
	q := []pair{}
	for i, x := 0, 1; i < m && x == 1; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '*' {
				q = append(q, pair{i, j})
				x = 0
				break
			}
		}
	}
	for len(q) > 0 {
		ans++
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p.i+dirs[k], p.j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == '#' {
						return ans
					}
					if grid[x][y] == 'O' {
						grid[x][y] = 'X'
						q = append(q, pair{x, y})
					}
				}
			}
		}
	}
	return -1
}