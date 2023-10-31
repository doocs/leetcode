func flipChess(chessboard []string) (ans int) {
	m, n := len(chessboard), len(chessboard[0])
	bfs := func(i, j int) int {
		q := [][2]int{{i, j}}
		g := make([][]byte, m)
		for i := range g {
			g[i] = make([]byte, n)
			copy(g[i], []byte(chessboard[i]))
		}
		g[i][j] = 'X'
		cnt := 0
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			i, j = p[0], p[1]
			for a := -1; a <= 1; a++ {
				for b := -1; b <= 1; b++ {
					if a == 0 && b == 0 {
						continue
					}
					x, y := i+a, j+b
					for x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O' {
						x, y = x+a, y+b
					}
					if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X' {
						x -= a
						y -= b
						cnt += max(abs(x-i), abs(y-j))
						for x != i || y != j {
							g[x][y] = 'X'
							q = append(q, [2]int{x, y})
							x -= a
							y -= b
						}
					}
				}
			}
		}
		return cnt
	}
	for i, row := range chessboard {
		for j, c := range row {
			if c == '.' {
				ans = max(ans, bfs(i, j))
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}