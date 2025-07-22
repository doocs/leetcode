func orangesRotting(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := [][2]int{}
	cnt := 0
	for i, row := range grid {
		for j, x := range row {
			if x == 1 {
				cnt++
			} else if x == 2 {
				q = append(q, [2]int{i, j})
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for ans := 1; len(q) > 0 && cnt > 0; ans++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for d := 0; d < 4; d++ {
				x, y := p[0]+dirs[d], p[1]+dirs[d+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
					grid[x][y] = 2
					q = append(q, [2]int{x, y})
					if cnt--; cnt == 0 {
						return ans
					}
				}
			}
		}
	}
	if cnt > 0 {
		return -1
	}
	return 0
}
