func countServers(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	row, col := make([]int, m), make([]int, n)
	for i := range grid {
		for j, x := range grid[i] {
			if x == 1 {
				row[i]++
				col[j]++
			}
		}
	}
	for i := range grid {
		for j, x := range grid[i] {
			if x == 1 && (row[i] > 1 || col[j] > 1) {
				ans++
			}
		}
	}
	return
}