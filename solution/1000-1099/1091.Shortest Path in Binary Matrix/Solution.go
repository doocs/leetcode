func shortestPathBinaryMatrix(grid [][]int) int {
	if grid[0][0] == 1 {
		return -1
	}
	n := len(grid)
	grid[0][0] = 1
	q := [][2]int{{0, 0}}
	for ans := 1; len(q) > 0; ans++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			i, j := p[0], p[1]
			q = q[1:]
			if i == n-1 && j == n-1 {
				return ans
			}
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
						grid[x][y] = 1
						q = append(q, [2]int{x, y})
					}
				}
			}
		}
	}
	return -1
}