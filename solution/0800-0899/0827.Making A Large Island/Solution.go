var p []int
var size []int
var n int
var mx int

func largestIsland(grid [][]int) int {
	n, mx = len(grid), 1
	p = make([]int, n*n)
	size = make([]int, n*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
		size[i] = 1
	}

	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						union(i*n+j, (i+e[0])*n+j+e[1])
					}
				}
			}
		}
	}
	res := mx
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				t := 1
				s := make(map[int]bool)
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						root := find((i+e[0])*n + j + e[1])
						if !s[root] {
							t += size[root]
							s[root] = true
						}
					}
				}
				res = max(res, t)
			}
		}
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
		mx = max(mx, size[pb])
		p[pa] = pb
	}
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}