var p []int

func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	p = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' {
				if i == 0 || j == 0 || i == m-1 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for _, e := range dirs {
						if board[i+e[0]][j+e[1]] == 'O' {
							p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
						}
					}
				}
			}

		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' && find(i*n+j) != find(m*n) {
				board[i][j] = 'X'
			}
		}
	}
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}