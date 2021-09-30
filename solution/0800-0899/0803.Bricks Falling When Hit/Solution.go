var p []int
var size []int
var g [][]int
var m int
var n int

func hitBricks(grid [][]int, hits [][]int) []int {
	m, n = len(grid), len(grid[0])
	p = make([]int, m*n+1)
	size = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
		size[i] = 1
	}
	g = make([][]int, m)
	for i := 0; i < m; i++ {
		g[i] = make([]int, n)
		for j := 0; j < n; j++ {
			g[i][j] = grid[i][j]
		}
	}
	for _, e := range hits {
		g[e[0]][e[1]] = 0
	}
	for j := 0; j < n; j++ {
		if g[0][j] == 1 {
			union(j, m*n)
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if g[i][j] == 0 {
				continue
			}
			if g[i-1][j] == 1 {
				union(i*n+j, (i-1)*n+j)
			}
			if j > 0 && g[i][j-1] == 1 {
				union(i*n+j, i*n+j-1)
			}
		}
	}

	res := make([]int, len(hits))
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for k := len(hits) - 1; k >= 0; k-- {
		i, j := hits[k][0], hits[k][1]
		if grid[i][j] == 0 {
			continue
		}
		origin := size[find(m*n)]
		if i == 0 {
			union(j, m*n)
		}
		for _, dir := range dirs {
			if check(i+dir[0], j+dir[1]) {
				union(i*n+j, (i+dir[0])*n+j+dir[1])
			}
		}
		cur := size[find(m*n)]
		res[k] = max(0, cur-origin-1)
		g[i][j] = 1
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa != pb {
		size[pb] += size[pa]
		p[pa] = pb
	}
}

func check(i, j int) bool {
	return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}