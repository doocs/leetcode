func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	p := make([]int, m*n+1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' {
				if i == 0 || i == m-1 || j == 0 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for k := 0; k < 4; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if board[x][y] == 'O' {
							p[find(x*n+y)] = find(i*n + j)
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