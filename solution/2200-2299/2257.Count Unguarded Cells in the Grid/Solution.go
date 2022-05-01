func countUnguarded(m int, n int, guards [][]int, walls [][]int) int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for _, e := range guards {
		g[e[0]][e[1]] = 1
	}
	for _, e := range walls {
		g[e[0]][e[1]] = 2
	}
	dirs := [][]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, p := range guards {
		for _, dir := range dirs {
			a, b := dir[0], dir[1]
			x, y := p[0], p[1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && g[x+a][y+b] != 1 && g[x+a][y+b] != 2 {
				x, y = x+a, y+b
				g[x][y] = 3
			}
		}
	}
	ans := 0
	for _, row := range g {
		for _, v := range row {
			if v == 0 {
				ans++
			}
		}
	}
	return ans
}