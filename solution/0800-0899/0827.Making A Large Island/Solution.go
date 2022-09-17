func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([]int, n*n)
	size := make([]int, n*n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 1
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						pa, pb := find(x*n+y), find(i*n+j)
						if pa != pb {
							p[pa] = pb
							size[pb] += size[pa]
							ans = max(ans, size[pb])
						}
					}
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						root := find(x*n + y)
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += size[root]
						}
					}
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}