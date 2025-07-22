func maxMoves(kx int, ky int, positions [][]int) int {
	n := len(positions)
	const m = 50
	dx := []int{1, 1, 2, 2, -1, -1, -2, -2}
	dy := []int{2, -2, 1, -1, 2, -2, 1, -1}
	dist := make([][][]int, n+1)
	for i := range dist {
		dist[i] = make([][]int, m)
		for j := range dist[i] {
			dist[i][j] = make([]int, m)
			for k := range dist[i][j] {
				dist[i][j][k] = -1
			}
		}
	}

	for i := 0; i <= n; i++ {
		x := kx
		y := ky
		if i < n {
			x = positions[i][0]
			y = positions[i][1]
		}
		q := [][2]int{[2]int{x, y}}
		dist[i][x][y] = 0

		for step := 1; len(q) > 0; step++ {
			for k := len(q); k > 0; k-- {
				p := q[0]
				q = q[1:]
				x1, y1 := p[0], p[1]
				for j := 0; j < 8; j++ {
					x2 := x1 + dx[j]
					y2 := y1 + dy[j]
					if x2 >= 0 && x2 < m && y2 >= 0 && y2 < m && dist[i][x2][y2] == -1 {
						dist[i][x2][y2] = step
						q = append(q, [2]int{x2, y2})
					}
				}
			}
		}
	}
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, 1<<n)
		for j := range f[i] {
			f[i][j] = make([]int, 2)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(last, state, k int) int
	dfs = func(last, state, k int) int {
		if state == 0 {
			return 0
		}
		if f[last][state][k] != -1 {
			return f[last][state][k]
		}

		var res int
		if k == 0 {
			res = math.MaxInt32
		}

		for i, p := range positions {
			x, y := p[0], p[1]
			if (state>>i)&1 == 1 {
				t := dfs(i, state^(1<<i), k^1) + dist[last][x][y]
				if k == 1 {
					res = max(res, t)
				} else {
					res = min(res, t)
				}
			}
		}
		f[last][state][k] = res
		return res
	}

	return dfs(n, (1<<n)-1, 1)
}
