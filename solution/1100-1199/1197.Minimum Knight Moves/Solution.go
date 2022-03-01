func minKnightMoves(x int, y int) int {
	x, y = x+310, y+310
	ans := 0
	q := [][]int{{310, 310}}
	vis := make([][]bool, 700)
	for i := range vis {
		vis[i] = make([]bool, 700)
	}
	dirs := [][]int{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p[0] == x && p[1] == y {
				return ans
			}
			for _, dir := range dirs {
				c, d := p[0]+dir[0], p[1]+dir[1]
				if !vis[c][d] {
					vis[c][d] = true
					q = append(q, []int{c, d})
				}
			}
		}
		ans++
	}
	return -1
}