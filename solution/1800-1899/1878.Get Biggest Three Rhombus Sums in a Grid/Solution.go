func getBiggestThree(grid [][]int) []int {
	m, n := len(grid), len(grid[0])
	s1 := make([][]int, m+1)
	s2 := make([][]int, m+1)
	for i := range s1 {
		s1[i] = make([]int, n+2)
		s2[i] = make([]int, n+2)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s1[i][j] = s1[i-1][j-1] + grid[i-1][j-1]
			s2[i][j] = s2[i-1][j+1] + grid[i-1][j-1]
		}
	}

	ss := treemap.NewWithIntComparator()
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			l := min(i-1, m-i, j-1, n-j)
			ss.Put(grid[i-1][j-1], nil)
			for k := 1; k <= l; k++ {
				a := s1[i+k][j] - s1[i][j-k]
				b := s1[i][j+k] - s1[i-k][j]
				c := s2[i][j-k] - s2[i-k][j]
				d := s2[i+k][j] - s2[i][j+k]
				ss.Put(a+b+c+d-grid[i+k-1][j-1]+grid[i-k-1][j-1], nil)
			}
			for ss.Size() > 3 {
				x, _ := ss.Min()
				ss.Remove(x.(int))
			}
		}
	}
	ans := make([]int, ss.Size())
	for i, k := range ss.Keys() {
		ans[len(ans)-i-1] = k.(int)
	}
	return ans
}