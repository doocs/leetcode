func minimumSeconds(land [][]string) int {
	m, n := len(land), len(land[0])
	vis := make([][]bool, m)
	g := make([][]int, m)
	q := [][2]int{}
	var si, sj int
	for i, row := range land {
		vis[i] = make([]bool, n)
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = 1 << 30
		}
		for j, c := range row {
			if c == "*" {
				q = append(q, [2]int{i, j})
			} else if c == "S" {
				si, sj = i, j
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for t := 0; len(q) > 0; t++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			g[i][j] = t
			for d := 0; d < 4; d++ {
				x, y := i+dirs[d], j+dirs[d+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] {
					empty := land[x][y] == "."
					start := land[x][y] == "S"
					if empty || start {
						vis[x][y] = true
						q = append(q, [2]int{x, y})
					}
				}
			}
		}
	}
	q = append(q, [2]int{si, sj})
	vis = make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	vis[si][sj] = true
	for t := 0; len(q) > 0; t++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if land[i][j] == "D" {
				return t
			}
			for d := 0; d < 4; d++ {
				x, y := i+dirs[d], j+dirs[d+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && g[x][y] > t+1 {
					empty := land[x][y] == "."
					dest := land[x][y] == "D"
					if empty || dest {
						vis[x][y] = true
						q = append(q, [2]int{x, y})
					}
				}
			}
		}
	}
	return -1
}