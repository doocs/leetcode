func shortestPathAllKeys(grid []string) int {
	m, n := len(grid), len(grid[0])
	cnt := 0
	sx, sy := 0, 0
	for i, row := range grid {
		for j, c := range row {
			if 'a' <= c && c <= 'z' {
				cnt++
			} else if c == '@' {
				sx, sy = i, j
			}
		}
	}
	q := [][]int{{sx, sy, 0}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, 1<<cnt)
		}
	}
	vis[sx][sy][0] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	mask := (1 << cnt) - 1
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, j, state := p[0], p[1], p[2]
			if state == mask {
				return ans
			}
			for k := 0; k < 4; k++ {
				nxt := state
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					c := grid[x][y]
					if c == '#' {
						continue
					}
					if 'A' <= c && c <= 'Z' && (nxt&(1<<(c-'A'))) == 0 {
						continue
					}
					if 'a' <= c && c <= 'z' {
						nxt |= 1 << (c - 'a')
					}
					if !vis[x][y][nxt] {
						vis[x][y][nxt] = true
						q = append(q, []int{x, y, nxt})
					}
				}
			}
		}
		ans++
	}
	return -1
}