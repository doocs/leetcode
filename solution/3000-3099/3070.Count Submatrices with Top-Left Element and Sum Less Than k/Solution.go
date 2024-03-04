func countSubmatrices(grid [][]int, k int) (ans int) {
	s := make([][]int, len(grid)+1)
	for i := range s {
		s[i] = make([]int, len(grid[0])+1)
	}
	for i, row := range grid {
		for j, x := range row {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + x
			if s[i+1][j+1] <= k {
				ans++
			}
		}
	}
	return
}