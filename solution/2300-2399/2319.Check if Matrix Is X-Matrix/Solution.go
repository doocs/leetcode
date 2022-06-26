func checkXMatrix(grid [][]int) bool {
	n := len(grid)
	for i, row := range grid {
		for j, v := range row {
			if i == j || i == n-j-1 {
				if v == 0 {
					return false
				}
			} else if v != 0 {
				return false
			}
		}
	}
	return true
}