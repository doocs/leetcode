func queensAttacktheKing(queens [][]int, king []int) [][]int {
	s := make(map[int]bool)
	n := 8
	for _, queen := range queens {
		s[queen[0]*n+queen[1]] = true
	}
	dirs := [8][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	var ans [][]int
	for _, dir := range dirs {
		x, y := king[0], king[1]
		a, b := dir[0], dir[1]
		for x+a >= 0 && x+a < n && y+b >= 0 && y+b < n {
			x, y = x+a, y+b
			if s[x*n+y] {
				ans = append(ans, []int{x, y})
				break
			}
		}
	}
	return ans
}