func orangesRotting(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt := 0
	var q [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 2 {
				q = append(q, []int{i, j})
			} else if grid[i][j] == 1 {
				cnt++
			}
		}
	}
	ans := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 && cnt > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
					cnt--
					grid[x][y] = 2
					q = append(q, []int{x, y})
				}
			}
		}
	}
	if cnt > 0 {
		return -1
	}
	return ans
}