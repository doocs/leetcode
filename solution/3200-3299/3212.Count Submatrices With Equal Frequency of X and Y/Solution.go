func numberOfSubmatrices(grid [][]byte) (ans int) {
	m, n := len(grid), len(grid[0])
	s := make([][][]int, m+1)
	for i := range s {
		s[i] = make([][]int, n+1)
		for j := range s[i] {
			s[i][j] = make([]int, 2)
		}
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j][0] = s[i-1][j][0] + s[i][j-1][0] - s[i-1][j-1][0]
			if grid[i-1][j-1] == 'X' {
				s[i][j][0]++
			}
			s[i][j][1] = s[i-1][j][1] + s[i][j-1][1] - s[i-1][j-1][1]
			if grid[i-1][j-1] == 'Y' {
				s[i][j][1]++
			}
			if s[i][j][0] > 0 && s[i][j][0] == s[i][j][1] {
				ans++
			}
		}
	}
	return
}