func checkValidGrid(grid [][]int) bool {
	if grid[0][0] != 0 {
		return false
	}
	n := len(grid)
	type pair struct{ x, y int }
	pos := make([]pair, n*n)
	for i, row := range grid {
		for j, x := range row {
			pos[x] = pair{i, j}
		}
	}
	for i := 1; i < n*n; i++ {
		p1, p2 := pos[i-1], pos[i]
		dx := abs(p1.x - p2.x)
		dy := abs(p1.y - p2.y)
		ok := (dx == 2 && dy == 1) || (dx == 1 && dy == 2)
		if !ok {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}