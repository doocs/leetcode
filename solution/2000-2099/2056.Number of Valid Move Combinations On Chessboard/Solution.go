func countCombinations(pieces []string, positions [][]int) (ans int) {
	n := len(pieces)
	m := 9
	dist := make([][][]int, n)
	for i := range dist {
		dist[i] = make([][]int, m)
		for j := range dist[i] {
			dist[i][j] = make([]int, m)
		}
	}

	end := make([][3]int, n)

	rookDirs := [][2]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
	bishopDirs := [][2]int{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	queenDirs := [][2]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}

	resetDist := func(i int) {
		for j := 0; j < m; j++ {
			for k := 0; k < m; k++ {
				dist[i][j][k] = -1
			}
		}
	}

	checkStop := func(i, x, y, t int) bool {
		for j := 0; j < i; j++ {
			if dist[j][x][y] >= t {
				return false
			}
		}
		return true
	}

	checkPass := func(i, x, y, t int) bool {
		for j := 0; j < i; j++ {
			if dist[j][x][y] == t {
				return false
			}
			if end[j][0] == x && end[j][1] == y && end[j][2] <= t {
				return false
			}
		}
		return true
	}

	isValid := func(x, y int) bool {
		return x >= 1 && x < m && y >= 1 && y < m
	}

	getDirs := func(piece string) [][2]int {
		switch piece[0] {
		case 'r':
			return rookDirs
		case 'b':
			return bishopDirs
		default:
			return queenDirs
		}
	}

	var dfs func(i int)
	dfs = func(i int) {
		if i >= n {
			ans++
			return
		}

		x, y := positions[i][0], positions[i][1]
		resetDist(i)
		dist[i][x][y] = 0
		end[i] = [3]int{x, y, 0}

		if checkStop(i, x, y, 0) {
			dfs(i + 1)
		}

		dirs := getDirs(pieces[i])
		for _, dir := range dirs {
			resetDist(i)
			dist[i][x][y] = 0
			nx, ny, nt := x+dir[0], y+dir[1], 1

			for isValid(nx, ny) && checkPass(i, nx, ny, nt) {
				dist[i][nx][ny] = nt
				end[i] = [3]int{nx, ny, nt}
				if checkStop(i, nx, ny, nt) {
					dfs(i + 1)
				}
				nx += dir[0]
				ny += dir[1]
				nt++
			}
		}
	}

	dfs(0)
	return
}
