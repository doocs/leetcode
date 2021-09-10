var p []int

func numIslands2(m int, n int, positions [][]int) []int {
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var res []int
	cur := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, position := range positions {
		i, j := position[0], position[1]
		if grid[i][j] == 1 {
			res = append(res, cur)
			continue
		}
		grid[i][j] = 1
		cur++
		for _, e := range dirs {
			if check(i+e[0], j+e[1], grid) && find(i*n+j) != find((i+e[0])*n+j+e[1]) {
				p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
				cur--
			}
		}
		res = append(res, cur)
	}
	return res
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < len(grid) && j >= 0 && j < len(grid[0]) && grid[i][j] == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}