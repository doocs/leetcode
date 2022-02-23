func maximumMinimumPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	vis := make([][]bool, m)
	var scores [][]int
	for i := range vis {
		vis[i] = make([]bool, n)
		for j := range grid[i] {
			scores = append(scores, []int{grid[i][j], i, j})
		}
	}
	sort.Slice(scores, func(i, j int) bool {
		return scores[i][0] > scores[j][0]
	})
	vis[0][0] = true
	vis[m-1][n-1] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := min(grid[0][0], grid[m-1][n-1])
	for find(0) != find(m*n-1) {
		t := scores[0]
		scores = scores[1:]
		score, i, j := t[0], t[1], t[2]
		vis[i][j] = true
		ans = min(ans, score)
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && vis[x][y] {
				p[find(x*n+y)] = find(i*n + j)
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}