func canMakeSquare(grid [][]byte) bool {
	dirs := [5]int{0, 0, 1, 1, 0}
	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			cnt1, cnt2 := 0, 0
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if grid[x][y] == 'W' {
					cnt1++
				} else {
					cnt2++
				}
			}
			if cnt1 != cnt2 {
				return true
			}
		}
	}
	return false
}