func countUnguarded(m int, n int, guards [][]int, walls [][]int) (ans int) {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for _, e := range guards {
		g[e[0]][e[1]] = 2
	}
	for _, e := range walls {
		g[e[0]][e[1]] = 2
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, e := range guards {
		for k := 0; k < 4; k++ {
			x, y := e[0], e[1]
			a, b := dirs[k], dirs[k+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && g[x+a][y+b] < 2 {
				x, y = x+a, y+b
				g[x][y] = 1
			}
		}
	}
	for _, row := range g {
		for _, v := range row {
			if v == 0 {
				ans++
			}
		}
	}
	return
}