func minMovesToCaptureTheQueen(a int, b int, c int, d int, e int, f int) int {
	dirs := [2][5]int{{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}}
	check := func(i, sx, sy, bx, by int) bool {
		for d := 0; d < 4; d++ {
			for k := 1; k < 8; k++ {
				x := sx + dirs[i][d]*k
				y := sy + dirs[i][d+1]*k
				if x < 1 || x > 8 || y < 1 || y > 8 || (x == bx && y == by) {
					break
				}
				if x == e && y == f {
					return true
				}
			}
		}
		return false
	}
	if check(0, a, b, c, d) || check(1, c, d, a, b) {
		return 1
	}
	return 2
}