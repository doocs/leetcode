var p []int

func latestDayToCross(row int, col int, cells [][]int) int {
	n := row * col
	p = make([]int, n+2)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]bool, row)
	for i := 0; i < row; i++ {
		grid[i] = make([]bool, col)
	}
	top, bottom := n, n+1
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for k := len(cells) - 1; k >= 0; k-- {
		i, j := cells[k][0]-1, cells[k][1]-1
		grid[i][j] = true
		for _, e := range dirs {
			if check(i+e[0], j+e[1], grid) {
				p[find(i*col+j)] = find((i+e[0])*col + j + e[1])
			}
		}
		if i == 0 {
			p[find(i*col+j)] = find(top)
		}
		if i == row-1 {
			p[find(i*col+j)] = find(bottom)
		}
		if find(top) == find(bottom) {
			return k
		}
	}
	return 0
}

func check(i, j int, grid [][]bool) bool {
	return i >= 0 && i < len(grid) && j >= 0 && j < len(grid[0]) && grid[i][j]
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}