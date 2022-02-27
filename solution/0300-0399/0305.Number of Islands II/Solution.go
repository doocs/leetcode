func numIslands2(m int, n int, positions [][]int) []int {
	p := make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	var ans []int
	cnt := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for _, pos := range positions {
		i, j := pos[0], pos[1]
		if grid[i][j] == 1 {
			ans = append(ans, cnt)
			continue
		}
		grid[i][j] = 1
		cnt++
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x*n+y) != find(i*n+j) {
				p[find(x*n+y)] = find(i*n + j)
				cnt--
			}
		}
		ans = append(ans, cnt)
	}
	return ans
}