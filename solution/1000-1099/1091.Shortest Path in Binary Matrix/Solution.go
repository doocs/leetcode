func shortestPathBinaryMatrix(grid [][]int) int {
	if grid[0][0] == 1 {
		return -1
	}
	n := len(grid)
	q := [][]int{[]int{0, 0}}
	grid[0][0] = 1
	ans := 0
	for len(q) > 0 {
		ans++
		for m := len(q); m > 0; m-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if i == n-1 && j == n-1 {
				return ans
			}
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
						q = append(q, []int{x, y})
						grid[x][y] = 1
					}
				}
			}
		}
	}
	return -1
}