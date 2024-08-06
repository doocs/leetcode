func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt1, cnt2 := 0, 0
	for _, row := range grid {
		for j := 0; j < n/2; j++ {
			if row[j] != row[n-j-1] {
				cnt1++
			}
		}
	}
	for j := 0; j < n; j++ {
		for i := 0; i < m/2; i++ {
			if grid[i][j] != grid[m-i-1][j] {
				cnt2++
			}
		}
	}
	return min(cnt1, cnt2)
}
