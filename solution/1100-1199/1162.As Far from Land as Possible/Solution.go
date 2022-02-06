func maxDistance(grid [][]int) int {
	n := len(grid)
	var q [][]int
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				q = append(q, []int{i, j})
			}
		}
	}
	ans := -1
	valid := false
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for i := 0; i < 4; i++ {
				x, y := p[0]+dirs[i], p[1]+dirs[i+1]
				if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
					valid = true
					grid[x][y] = 1
					q = append(q, []int{x, y})
				}
			}
		}
	}
	if valid {
		return ans
	}
	return -1
}