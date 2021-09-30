var p []int
var n int

func swimInWater(grid [][]int) int {
	n = len(grid)
	p = make([]int, n*n)
	hi := make([]int, n*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			hi[grid[i][j]] = index(i, j)
		}
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for h := 0; h < n*n; h++ {
		x, y := hi[h]/n, hi[h]%n
		for _, dir := range dirs {
			x1, y1 := x+dir[0], y+dir[1]
			if check(x1, y1) && grid[x1][y1] <= h {
				p[find(index(x1, y1))] = find(hi[h])
			}
			if find(0) == find(n*n-1) {
				return h
			}
		}
	}
	return -1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func index(i, j int) int {
	return i*n + j
}

func check(i, j int) bool {
	return i >= 0 && i < n && j >= 0 && j < n
}