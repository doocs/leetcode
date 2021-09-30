var p []int

func maxAreaOfIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	size := make([]int, m*n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			p[i*n+j] = i*n + j
			size[i*n+j] = 1
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				if i < m-1 && grid[i+1][j] == 1 {
					a, b := find(i*n+j), find((i+1)*n+j)
					if a != b {
						size[a] += size[b]
					}
					p[b] = a
				}
				if j < n-1 && grid[i][j+1] == 1 {
					a, b := find(i*n+j), find(i*n+j+1)
					if a != b {
						size[a] += size[b]
					}
					p[b] = a
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				res = max(res, size[i*n+j])
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