func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	q := map[int]bool{}
	for i := range grid {
		q[i] = true
	}
	for j := 0; j < n-1; j++ {
		t := map[int]bool{}
		for i := range q {
			for k := i - 1; k <= i+1; k++ {
				if k >= 0 && k < m && grid[i][j] < grid[k][j+1] {
					t[k] = true
				}
			}
		}
		if len(t) == 0 {
			return j
		}
		q = t
	}
	return n - 1
}