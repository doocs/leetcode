func canPartitionGrid(grid [][]int) bool {
	s := 0
	for _, row := range grid {
		for _, x := range row {
			s += x
		}
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(grid), len(grid[0])
	pre := 0
	for i, row := range grid {
		for _, x := range row {
			pre += x
		}
		if pre*2 == s && i+1 < m {
			return true
		}
	}
	pre = 0
	for j := 0; j < n; j++ {
		for i := 0; i < m; i++ {
			pre += grid[i][j]
		}
		if pre*2 == s && j+1 < n {
			return true
		}
	}
	return false
}