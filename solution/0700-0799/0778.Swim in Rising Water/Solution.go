func swimInWater(grid [][]int) int {
	n := len(grid)
	p := make([]int, n*n)
	for i := range p {
		p[i] = i
	}
	hi := make([]int, n*n)
	for i, row := range grid {
		for j, h := range row {
			hi[h] = i*n + j
		}
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for t := 0; t < n*n; t++ {
		i, j := hi[t]/n, hi[t]%n
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t {
				p[find(x*n+y)] = find(hi[t])
			}
			if find(0) == find(n*n-1) {
				return t
			}
		}
	}
	return -1
}