func queensAttacktheKing(queens [][]int, king []int) (ans [][]int) {
	n := 8
	s := [8][8]bool{}
	for _, q := range queens {
		s[q[0]][q[1]] = true
	}
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a != 0 || b != 0 {
				x, y := king[0]+a, king[1]+b
				for 0 <= x && x < n && 0 <= y && y < n {
					if s[x][y] {
						ans = append(ans, []int{x, y})
						break
					}
					x += a
					y += b
				}
			}
		}
	}
	return
}