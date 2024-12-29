func minimumOperations(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	for j := 0; j < n; j++ {
		pre := -1
		for i := 0; i < m; i++ {
			cur := grid[i][j]
			if pre < cur {
				pre = cur
			} else {
				pre++
				ans += pre - cur
			}
		}
	}
	return
}
