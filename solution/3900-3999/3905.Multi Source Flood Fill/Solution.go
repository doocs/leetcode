func colorGrid(n int, m int, sources [][]int) [][]int {
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	q := make([][]int, len(sources))
	copy(q, sources)
	dirs := []int{-1, 0, 1, 0, -1}
	for _, s := range q {
		ans[s[0]][s[1]] = s[2]
	}
	for len(q) > 0 {
		vis := make(map[[2]int]int)
		for _, curr := range q {
			r, c, color := curr[0], curr[1], curr[2]
			for i := 0; i < 4; i++ {
				x, y := r+dirs[i], c+dirs[i+1]
				if x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0 {
					if color > vis[[2]int{x, y}] {
						vis[[2]int{x, y}] = color
					}
				}
			}
		}
		q = nil
		for pos, color := range vis {
			ans[pos[0]][pos[1]] = color
			q = append(q, []int{pos[0], pos[1], color})
		}
	}
	return ans
}
