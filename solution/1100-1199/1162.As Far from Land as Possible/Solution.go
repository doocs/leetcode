func maxDistance(grid [][]int) int {
	n := len(grid)
	q := [][2]int{}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				q = append(q, [2]int{i, j})
			}
		}
	}
	ans := -1
	if len(q) == 0 || len(q) == n*n {
		return ans
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
					grid[x][y] = 1
					q = append(q, [2]int{x, y})
				}
			}
		}
		ans++
	}
	return ans
}