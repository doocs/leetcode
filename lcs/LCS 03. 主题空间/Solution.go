var p []int

func largestArea(grid []string) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}

	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || i == m-1 || j == 0 || j == n-1 || grid[i][j] == '0' {
				p[find(i*n+j)] = find(m * n)
			} else {
				for _, e := range dirs {
					if grid[i+e[0]][j+e[1]] == '0' || grid[i][j] == grid[i+e[0]][j+e[1]] {
						p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
					}
				}
			}
		}
	}
	mp := make(map[int]int, 0)
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			root := find(i*n + j)
			if root != find(m*n) {
				mp[root]++
				res = max(res, mp[root])
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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}