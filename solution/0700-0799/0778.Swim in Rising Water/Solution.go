func swimInWater(grid [][]int) int {
	n := len(grid)
	m := n * n
	p := make([]int, m)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	hi := make([]int, m)
	for i := range grid {
		for j, h := range grid[i] {
			hi[h] = i*n + j
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for t := 0; t < m; t++ {
		id := hi[t]
		x, y := id/n, id%n
		for k := 0; k < 4; k++ {
			nx, ny := x+dirs[k], y+dirs[k+1]
			if nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t {
				a := find(x*n + y)
				b := find(nx*n + ny)
				p[a] = b
			}
		}
		if find(0) == find(m-1) {
			return t
		}
	}
	return 0
}
