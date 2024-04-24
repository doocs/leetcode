func minimumOperations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 10)
		for j := range f[i] {
			f[i][j] = 1 << 29
		}
	}
	for i := 0; i < n; i++ {
		cnt := [10]int{}
		for j := 0; j < m; j++ {
			cnt[grid[j][i]]++
		}
		if i == 0 {
			for j := 0; j < 10; j++ {
				f[i][j] = m - cnt[j]
			}
		} else {
			for j := 0; j < 10; j++ {
				for k := 0; k < 10; k++ {
					if j != k {
						f[i][j] = min(f[i][j], f[i-1][k]+m-cnt[j])
					}
				}
			}
		}
	}
	return slices.Min(f[n-1])
}